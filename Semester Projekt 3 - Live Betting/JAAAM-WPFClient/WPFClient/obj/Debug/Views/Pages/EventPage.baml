   M S B A M L   `   `   `  ����D  @WPFClient, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null=#clr-namespace:WPFClient.Views.PagesWPFClient.Views.Pages  5      %    WPFClient.Views.Pages.EventPage   5      W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5      R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5      Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5      @d2http://schemas.microsoft.com/expression/blend/2008     5      5local#clr-namespace:WPFClient.Views.Pages     5        N� Title$  	EventPage��5	      .��5      � 
�������.+�5   
   �� $��Event Details��6   $
��Left=�6(   $��	10,10,0,0q�6C   $	��Top=�6V   $	��124��6n   �� 5   
   -   5        $��searchForEventInput��5   `   $
��Left=�6   $��23��6-   $��	14,67,0,0q�69    �� TextWrapping$
 Wrap=�6L   $��Search for Event��6{   $	��Top=�6�   $	��196��6�   �� 5   
   -   5        $��Search��5      $
��Left=�6"   $��
225,67,0,0q�6=   $	��Top=�6Q   $	��119��6i   $��23��6�   �� 5   
   -   5        $��eventNameInput��5      $
��Left=�6(   $��23��6C   $��425,115,0,0q�6O   $
 Wrap=�6d   $��Enter event name��6x   $	��Top=�6�   $	��239��6�   �� 5   
   -   5        $��eventViewList��5      $
��Left=�6(   $	��201��6C   $��
14,115,0,0q�6P   $	��Top=�6d   $	��330��6|    �� View 5      �� 5      .P�5      �� $
��Name��6%    �� Width$	 100��63    �� DisplayMemberBinding 6?   �� *Name+�� 5      $��	Game Name��6%   $	 100��68    6D   �� *
GameName+�� 5      $��
Event Type��6%   $	 120��69    6E   �� *Type+5      5      �� 5   
   -   5        $��eventGameNameInput��5      $
��Left=�6,   $��23��6G   $��425,164,0,0q�6S   $
 Wrap=�6h   $��Enter game name��6|   $	��Top=�6�   $	��239��6�   �� 5   
   -   5        $��eventTypeInput��5      $
��Left=�6(   $��23��6C   $��425,211,0,0q�6O   $
 Wrap=�6d   $��Enter event type��6x   $	��Top=�6�   $	��239��6�   �� 5   
   $��Create/Update Details��6   $
��Left=�60   $��
461,63,0,0q�6K   $	��Top=�6_   $	��168��6w   �� 5   
   -   5        $��Create��5      $
��Left=�6"   $��589,296,0,0q�6=   $	��Top=�6R   $��75��6j   6u   �� 5    
   -   5        $��Update��5       $
��Left=�6"   $��495,296,0,0q�6=   $	��Top=�6R   $��76��6j   6u   �� 5!   
   -	   5        $��Delete��5!      $
��Left=�6"   $��403,296,0,0q�6=   $	��Top=�6R   $��75��6j   6u   �� 5"   
   -
   5        $��	eventHeld��5"   (   $��
Event Held��6   $
��Left=�69   $��425,256,0,0q�6T   $	��Top=�6i   $��97��6�   �� 5#   
   -   5         ��Name$ 
btnDetails��5#      $��Details��6%   $
��Left=�67   $��589,256,0,0q�6R   $	��Top=�6g   $��75��6   6�   5%      5&      