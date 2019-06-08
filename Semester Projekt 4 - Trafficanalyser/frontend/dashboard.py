#Imports
import os, sys
import numpy as np

import dash
from dash.dependencies import Output, Input
import dash_daq as daq
import dash_core_components as dcc
import dash_html_components as html
import dash_table as dt

import plotly
import plotly.graph_objs as go

import random
from collections import deque

import pandas as pd

import urllib, json

from pathlib import Path

from dateutil import parser
import requests

#TO DO
# - Remove slicing in data get

roadspeed = 0.0
roadname = ''
unitid = 0


X = deque(maxlen=20)
y = deque(maxlen=20)
X.append(1)
y.append(1)
app = dash.Dash(__name__)
app.layout = html.Div(
    [
        html.H1('Trafficanalyser Dashboard'),

        html.H2('- Livedata pr. vejnavn -'),
        html.P('Indtast vejnavn:'),
        html.Div(id='output-roadname-button'),
        html.Div(dcc.Input(id='input-box_roadname', type='text')),
        html.Button('Vis data', id='button_roadname'),
        dcc.Graph(id ='live-update-byroad-graph', animate=False),
        dcc.Interval(
            id='interval-component-byroad-graph',
            interval=1000
            ),
        daq.Gauge(
            id='gauge',
            label='Gennemsnitshastighed for valgte vej',
            max=200,
            min=0,
            showCurrentValue=True,
            units='KPH'
            ),

        html.H2('- Livedata over alt data -'),
        dcc.Graph(id ='live-update-alldata-graph', animate=False),
        dcc.Interval(
            id='interval-component-alldata-graph',
            interval=1000
        ),

        html.H2('- Gennemsnit hastighed/afstand kontra tidsinterval -'),
        html.H3('Sammenhæng mellem hastighed og afstand over døgnet:'),
        dt.DataTable(id = 'table',
            style_cell={'width': '30px'}
        ),
        dcc.Interval(
            id='interval-table',
            interval=900000
        ),

        html.H2('- Profit Beregning -'),
        html.P('Indtast unitid:'),
        html.Div(id='output-unitid-button'),
        html.Div(dcc.Input(id='input-box_unitid', type='text')),
        html.Button('Vis data', id='button_unitid'),
        dt.DataTable(id = 'update-byunitid-table',
            style_cell={'width': '30px'}
        ), 

        html.H2('- Live Regression -'),
        html.H3('Sammenhæng mellem hastighed og afstand på biler:'),
        dcc.Graph(id ='live-update-regression-graph', animate=False),
        dcc.Interval(
            id='interval-component-regression-graph',
            interval=1000
        )
        
    ]
)



@app.callback(
    dash.dependencies.Output('output-roadname-button', 'children'),
    [dash.dependencies.Input('button_roadname', 'n_clicks')],
    [dash.dependencies.State('input-box_roadname', 'value')])
def update_roadname(n_clicks, value):
    global roadname
    roadname = value


@app.callback(
    dash.dependencies.Output('output-unitid-button', 'children'),
    [dash.dependencies.Input('button_unitid', 'n_clicks')],
    [dash.dependencies.State('input-box_unitid', 'value')])
def update_unitid(n_clicks, value):
    global unitid
    unitid = value

@app.callback(Output('live-update-byroad-graph','figure'), 
               [Input('interval-component-byroad-graph', 'n_intervals')])
def update_byroad_graph(n):
    url = 'http://127.0.0.1:5000/get/databyroad/{0}'.format(roadname)
    response = urllib.request.urlopen(url)
    jsondata = json.loads(response.read())

    df = pd.DataFrame(jsondata)

    X = df._id.values[-50:].astype('str')
    y = df.speed.values[-50:].astype('str')

    data = go.Scatter(
        x = list(X),
        y = list(y),
        name = 'Scatter',
        mode = 'lines+markers'
    )
    
    return {'data':[data],'layout':go.Layout(xaxis = dict(range=[min(X), max(X)]),
                                            yaxis = dict(range=[min(y), 500], autorange = True))}

@app.callback(
    dash.dependencies.Output('gauge', 'value'),
    [dash.dependencies.Input('interval-component-byroad-graph', 'n_intervals')]
)
def update_gauge(input):
    global roadspeed 
    url = 'http://127.0.0.1:5000/get/databyroad/{0}'.format(roadname)
    response = urllib.request.urlopen(url)
    jsondata = json.loads(response.read())
    
    df = pd.DataFrame(jsondata)
    roadspeed = df['roadspeed'][0]
    data = df['speed'].astype('float')
    return data.mean()


@app.callback(Output('live-update-alldata-graph','figure'), 
               [Input('interval-component-alldata-graph', 'n_intervals')])
def update_alldata_graph(input):
    url = 'http://127.0.0.1:5000/get/alldata'
    response = urllib.request.urlopen(url)
    jsondata = json.loads(response.read())

    df = pd.DataFrame(jsondata)

    X = df._id.values[-50:].astype('str')
    y = df.speed.values[-50:].astype('str')

    data = go.Bar(
        x = list(X),
        y = list(y),
        name = 'Bar'
    )
    
    return {'data':[data],'layout':go.Layout(xaxis = dict(range=[min(X), max(X)]),
                                            yaxis = dict(range=[min(y), 500], autorange = True))}

@app.callback([Output('table', 'data'),Output('table', 'columns')],
    [Input('interval-table', 'n_intervals')])
def update_table_data(input):
    url = 'http://127.0.0.1:5000/get/alldata'
    response = urllib.request.urlopen(url)
    jsondata = json.loads(response.read())

    df = pd.DataFrame(jsondata)

    df = df[['timestamp','distance','speed']].copy()

    aggr_df = pd.DataFrame(columns = ['Tidsinterval', 'Hastighed gennemsnit (kmh)', 'Afstand gennemsnit (m)'])

    aggr_speed_dict = {'00 - 03': [], '03 - 06': [], '06 - 09': [], '09 - 12': [], '12 - 15': [], '15 - 18': [], '18 - 21': [], '21 - 24': []}
    aggr_distance_dict = {'00 - 03': [], '03 - 06': [], '06 - 09': [], '09 - 12': [], '12 - 15': [], '15 - 18': [], '18 - 21': [], '21 - 24': []}

    time_intervals = ['00 - 03', '03 - 06', '06 - 09', '09 - 12', '12 - 15', '15 - 18', '18 - 21', '21 - 24']

    for i in range(len(time_intervals)):
        aggr_df.at[i, 'Tidsinterval'] = time_intervals[i]

    aggr_df['Hastighed gennemsnit (kmh)'] = 0.0
    aggr_df['Afstand gennemsnit (m)'] = 0.0

    for index, row in df.iterrows():
        dt = parser.parse(row[0])
        if(dt.hour >= 0 and dt.hour < 3):
            aggr_distance_dict.get('00 - 03').append(float(row[1]))
            aggr_speed_dict.get('00 - 03').append(float(row[2]))
        elif(dt.hour >= 3 and dt.hour < 6):
            aggr_distance_dict.get('03 - 06').append(float(row[1]))
            aggr_speed_dict.get('03 - 06').append(float(row[2]))
        elif(dt.hour >= 6 and dt.hour < 9):
            aggr_distance_dict.get('06 - 09').append(float(row[1]))
            aggr_speed_dict.get('06 - 09').append(float(row[2]))
        elif(dt.hour >= 9 and dt.hour <12):
            aggr_distance_dict.get('09 - 12').append(float(row[1]))
            aggr_speed_dict.get('09 - 12').append(float(row[2]))
        elif(dt.hour >= 12 and dt.hour < 15):
            aggr_distance_dict.get('12 - 15').append(float(row[1]))
            aggr_speed_dict.get('12 - 15').append(float(row[2]))
        elif(dt.hour >= 15 and dt.hour < 18):
            aggr_distance_dict.get('15 - 18').append(float(row[1]))
            aggr_speed_dict.get('15 - 18').append(float(row[2]))
        elif(dt.hour >= 21 and dt.hour < 24):
            aggr_distance_dict.get('18 - 21').append(float(row[1]))
            aggr_speed_dict.get('18 - 21').append(float(row[2]))
        elif(dt.hour >= 21 and dt.hour < 24):
            aggr_distance_dict.get('21 - 24').append(float(row[1]))
            aggr_speed_dict.get('21 - 24').append(float(row[2]))

    for key, value in aggr_speed_dict.items():
        temp_aggr_speed = 0.0
        if(len(value) != 0):
            for i in range(len(value)):
                temp_aggr_speed = temp_aggr_speed + value[i]
            if(key == '00 - 03'):
                aggr_df.at[0, 'Hastighed gennemsnit (kmh)'] = temp_aggr_speed / len(value)
            elif(key == '03 - 06'):
                aggr_df.at[1, 'Hastighed gennemsnit (kmh)'] = temp_aggr_speed / len(value)
            elif(key == '06 - 09'):
                aggr_df.at[2, 'Hastighed gennemsnit (kmh)'] = temp_aggr_speed / len(value)
            elif(key == '09 - 12'):
                aggr_df.at[3, 'Hastighed gennemsnit (kmh)'] = temp_aggr_speed / len(value)
            elif(key == '12 - 15'):
                aggr_df.at[4, 'Hastighed gennemsnit (kmh)'] = temp_aggr_speed / len(value)
            elif(key == '15 - 18'):
                aggr_df.at[5, 'Hastighed gennemsnit (kmh)'] = temp_aggr_speed / len(value)
            elif(key == '18 - 21'):
                aggr_df.at[6, 'Hastighed gennemsnit (kmh)'] = temp_aggr_speed / len(value)
            elif(key == '21 - 24'):
                aggr_df.at[7, 'Hastighed gennemsnit (kmh)'] = temp_aggr_speed / len(value)
    
    for key, value in aggr_distance_dict.items():
        temp_aggr_distance = 0.0
        if(len(value) != 0):
            for i in range(len(value)):
                temp_aggr_distance = temp_aggr_distance + value[i]
            if(key == '00 - 03'):
                aggr_df.at[0, 'Afstand gennemsnit (m)'] = temp_aggr_distance / len(value)
            elif(key == '03 - 06'):
                aggr_df.at[1, 'Afstand gennemsnit (m)'] = temp_aggr_distance / len(value)
            elif(key == '06 - 09'):
                aggr_df.at[2, 'Afstand gennemsnit (m)'] = temp_aggr_distance / len(value)
            elif(key == '09 - 12'):
                aggr_df.at[3, 'Afstand gennemsnit (m)'] = temp_aggr_distance / len(value)
            elif(key == '12 - 15'):
                aggr_df.at[4, 'Afstand gennemsnit (m)'] = temp_aggr_distance / len(value)
            elif(key == '15 - 18'):
                aggr_df.at[5, 'Afstand gennemsnit (m)'] = temp_aggr_distance / len(value)
            elif(key == '18 - 21'):
                aggr_df.at[6, 'Afstand gennemsnit (m)'] = temp_aggr_distance / len(value)
            elif(key == '21 - 24'):
                aggr_df.at[7, 'Afstand gennemsnit (m)'] = temp_aggr_distance / len(value)

    columns = [{'name': i, 'id': i} for i in aggr_df.columns]
    data = aggr_df.to_dict('records')
    
    return [data, columns]
 
@app.callback([Output('update-byunitid-table', 'data'),Output('update-byunitid-table', 'columns')],
            [Input('button_unitid', 'n_clicks')])
def update_unitid_table(input):
    url = 'http://127.0.0.1:5000/get/prediction/{0}'.format(unitid)
    response = urllib.request.urlopen(url)
    jsondata = json.loads(response.read())

    df = pd.DataFrame(jsondata)
    

    df.columns = ['Profit','NotUsed']
    df.drop(['NotUsed'], axis = 1, inplace = True) 

    columns = [{'name': i, 'id': i} for i in df.columns]
    data = df.to_dict('records')

    return [data, columns]


@app.callback(Output('live-update-regression-graph','figure'), 
               [Input('interval-component-regression-graph', 'n_intervals')])
def update_regression_graph(input):
    url = 'http://127.0.0.1:5000/get/calcregression'
    response = urllib.request.urlopen(url)
    jsondata = json.loads(response.read())


    y_test = jsondata['y_test']
    prediction = jsondata['predictions']
    #intercept = float(jsondata['lm.intercept_'])

    df1 = pd.DataFrame(list(y_test.items()),
                      columns=['id','speed'])
    df2 = pd.DataFrame(prediction,
                      columns=['prediction'])

    X = df1.speed.values[-50:].astype('str')
    y = df2.prediction.values[-50:].astype('str')

    data = go.Scatter(
        x = list(X),
        y = list(y),
        name = 'Scatter',
        mode = 'markers'
    )
    return {'data':[data],'layout':go.Layout(xaxis = dict(range=[min(X), 500], autorange = True),
                                            yaxis = dict(range=[min(y), 500], autorange = True))}

if __name__ == '__main__': 
    app.run_server(port=47000, debug=False)

