<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>Our Love Story-Download</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <style type="text/css">
        @font-face {
            font-family: digit;
            src: url('digital-7_mono.ttf') format("truetype");
        }
    </style>
    <link href="style/default.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="style/jquery.js"></script>
    <script type="text/javascript" src="style/garden.js"></script>
    <script type="text/javascript" src="style/functions.js"></script>
</head>

<body>
    <div id="mainDiv">
        <div id="content">
            <div id="code">
                <span class="comments">/**</span><br />
                Boy i = <span class="keyword">new</span> Boy(<span class="string">"小黑"</span>);<br />
                Girl u = <span class="keyword">new</span> Girl(<span class="string">"小雪雪"</span>);<br />
                <span class="comments">// I told you I love you. </span><br />
                i.love(u);<br />

<script type="text/javascript"><!--
google_ad_client = "ca-pub-3712320065678109";
/* lovead */
google_ad_slot = "0650322805";
google_ad_width = 320;
google_ad_height = 50;
//-->
</script>
<script type="text/javascript"
src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>
            </div>
            <div id="loveHeart">
                <canvas id="garden"></canvas>
                <div id="words">
                    <div id="messages">
<center>
<script type="text/javascript"><!--
google_ad_client = "ca-pub-3712320065678109";
/* lovead */
google_ad_slot = "0650322805";
google_ad_width = 320;
google_ad_height = 50;
//-->
</script>
<script type="text/javascript"
src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>
</center>
                        赵雪雪, 俺喜欢你！
                    </div>
                    <div id="loveu">
                        <div class="signature">- 三里村小黑</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        var offsetX = $("#loveHeart").width() / 2;
        var offsetY = $("#loveHeart").height() / 2 - 55;
        
        if (!document.createElement('canvas').getContext) {
            var msg = document.createElement("div");
            msg.id = "errorMsg";
            msg.innerHTML = "Your browser doesn't support HTML5!<br/>Recommend use Chrome 14+/IE 9+/Firefox 7+/Safari 4+"; 
            document.body.appendChild(msg);
            $("#code").css("display", "none")
            $("#copyright").css("position", "absolute");
            $("#copyright").css("bottom", "10px");
            document.execCommand("stop");
        } else {
            setTimeout(function () {
                adjustWordsPosition();
                startHeartAnimation();
            }, 10000);
            
            $("#accept").click(function(){
                $(this).hide();
                $("#elapseClock").show();
                var together = new Date();
                timeElapse(together);
                setInterval(function () {
                    timeElapse(together);
                }, 500);
            })
            adjustCodePosition();
            $("#code").typewriter();
        }
    </script>
</body>
</html>
