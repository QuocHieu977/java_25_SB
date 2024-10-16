var countDownDate=new Date('Dec 8, 2025 21:30:00').getTime();var x=setInterval(function(){var now=new Date().getTime();var distance=countDownDate-now;var days=Math.floor(distance/(1000*60*60*24));var hours=Math.floor((distance%(1000*60*60*24))/(1000*60*60));var minutes=Math.floor((distance%(1000*60*60))/(1000*60));var seconds=Math.floor((distance%(1000*60))/1000);document.getElementById('countdown').innerHTML="<span class='days'>"+
days+
"</span><span class='hours'>"+
hours+
"</span><span class='minutes'>"+
minutes+
"</span><span class='seconds'>"+
seconds+
'</span>';if(distance<0){clearInterval(x);document.getElementById('demo').innerHTML='EXPIRED';}},1000);