package com.yourname.workshop;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.apache.cordova.CordovaWebView;

/**
 * Created by c on 4/23/16.
 */
public class VideoviewService extends Service {

    int nSamplerate = 44;

    String in2u = "<!doctype html>\n" +
            "<html lang=\"en\">\n" +
            "    <head>\n" +
            "        <meta charset=\"utf-8\" />\n" +
            "        <title>Plyr - A simple HTML5 media player</title>\n" +
            "        <meta name=\"description\" content=\"A simple HTML5 media player with custom controls and WebVTT captions.\">\n" +
            "        <meta name=\"author\" content=\"Sam Potts\">\n" +
            "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "\n" +
            "        <!-- Styles -->\n" +
            "        <link rel=\"stylesheet\" href=\"../dist/plyr.css\">\n" +
            "\n" +
            "        <!-- Docs styles -->\n" +
            "        <link rel=\"stylesheet\" href=\"dist/docs.css\">\n" +
            "    </head>\n" +
            "    <body>\n" +
            "        <header>\n" +
            "            <h1>Plyr</h1>\n" +
            "            <p>A simple, accessible HTML5 media player by <a href=\"https://twitter.com/sam_potts\" target=\"_blank\">@sam_potts</a> from <a href=\"https://twitter.com/selz\" target=\"_blank\">@selz</a></p>\n" +
            "            <nav>\n" +
            "                <ul>\n" +
            "                    <li>\n" +
            "                        <a href=\"https://github.com/selz/plyr\" target=\"_blank\" class=\"btn btn--large btn--primary\" data-shr-network=\"github\">\n" +
            "                            <svg class=\"icon\"><use xlink:href=\"#icon-github\"/></svg>Download on GitHub\n" +
            "                        </a>\n" +
            "                    </li>\n" +
            "                    <li>\n" +
            "                        <a href=\"https://twitter.com/intent/tweet?text=A+simple+HTML5+media+player+with+custom+controls+and+WebVTT+captions.&url=http%3A%2F%2Fplyr.io&via=Sam_Potts\" target=\"_blank\" class=\"btn btn--large btn--twitter\" data-shr-network=\"twitter\">\n" +
            "                            <svg class=\"icon\"><use xlink:href=\"#icon-twitter\"/></svg>Tweet\n" +
            "                        </a>\n" +
            "                    </li>\n" +
            "                </ul>\n" +
            "            </nav>\n" +
            "        </header>\n" +
            "\n" +
            "        <main role=\"main\" id=\"main\">\n" +
            "            <nav class=\"btn__bar\">\n" +
            "                <ul>\n" +
            "                    <li class=\"active\">\n" +
            "                        <button type=\"button\" class=\"btn\" data-source=\"video\">Video</button>\n" +
            "                    </li>\n" +
            "                    <li>\n" +
            "                        <button type=\"button\" class=\"btn\" data-source=\"audio\">Audio</button>\n" +
            "                    </li>\n" +
            "                    <li>\n" +
            "                        <button type=\"button\" class=\"btn btn--youtube\" data-source=\"youtube\"><svg class=\"icon\"><use xlink:href=\"#icon-youtube\"/></svg>YouTube</button>\n" +
            "                    </li>\n" +
            "                    <li>\n" +
            "                        <button type=\"button\" class=\"btn btn--vimeo\" data-source=\"vimeo\"><svg class=\"icon\"><use xlink:href=\"#icon-vimeo\"/></svg>Vimeo</button>\n" +
            "                    </li>\n" +
            "                </ul>\n" +
            "            </nav>\n" +
            "            <section>\n" +
            "                <div class=\"js-media-player\">\n" +
            "                    <video poster=\"https://cdn.selz.com/plyr/1.5/View_From_A_Blue_Moon_Trailer-HD.jpg\" controls crossorigin>\n" +
            "                        <!-- Video files -->\n" +
            "                        <source src=\"https://cdn.selz.com/plyr/1.5/View_From_A_Blue_Moon_Trailer-HD.mp4\" type=\"video/mp4\">\n" +
            "                        <source src=\"https://cdn.selz.com/plyr/1.5/View_From_A_Blue_Moon_Trailer-HD.webm\" type=\"video/webm\">\n" +
            "\n" +
            "                        <!-- Text track file -->\n" +
            "                        <track kind=\"captions\" label=\"English\" srclang=\"en\" src=\"https://cdn.selz.com/plyr/1.5/View_From_A_Blue_Moon_Trailer-HD.en.vtt\" default>\n" +
            "\n" +
            "                        <!-- Fallback for browsers that don't support the <video> element -->\n" +
            "                        <a href=\"https://cdn.selz.com/plyr/1.0/movie.mp4\">Download</a>\n" +
            "                    </video>\n" +
            "                </div>\n" +
            "\n" +
            "                <ul>\n" +
            "                    <li class=\"plyr__cite plyr__cite--video\"><small><a href=\"http://viewfromabluemoon.com/\" target=\"_blank\">View From A Blue Moon</a> &copy; Brainfarm</small></li>\n" +
            "                    <li class=\"plyr__cite plyr__cite--audio\"><small><a href=\"http://www.kishibashi.com/\" target=\"_blank\">Kishi Bashi &ndash; &ldquo;It All Began With A Burst&rdquo;</a> &copy; Kishi Bashi</small></li>\n" +
            "                    <li class=\"plyr__cite plyr__cite--youtube\"><small><a href=\"https://www.youtube.com/watch?v=bTqVqk7FSmY\" target=\"_blank\">View From A Blue Moon</a> on <span class=\"color--youtube\"><svg class=\"icon\"><use xlink:href=\"#icon-youtube\"/></svg>YouTube</span></small>\n" +
            "                    <li class=\"plyr__cite plyr__cite--vimeo\"><small><a href=\"https://vimeo.com/ondemand/viewfromabluemoon4k\" target=\"_blank\">View From A Blue Moon</a> on <span class=\"color--vimeo\"><svg class=\"icon\"><use xlink:href=\"#icon-vimeo\"/></svg>Vimeo</span></small>\n" +
            "                </ul>\n" +
            "            </section>\n" +
            "        </main>\n" +
            "\n" +
            "        <!-- Load SVG defs -->\n" +
            "        <!-- You should bundle all SVG/Icons into one file using a build tool such as gulp and svg store -->\n" +
            "        <script>\n" +
            "        (function() {\n" +
            "            [\n" +
            "                '../dist/sprite.svg',\n" +
            "                'dist/docs.svg'\n" +
            "            ]\n" +
            "            .forEach(function(u) {\n" +
            "                var x = new XMLHttpRequest(),\n" +
            "                    b = document.body;\n" +
            "                // Check for CORS support\n" +
            "                // If you're loading from same domain, you can remove the whole if/else statement\n" +
            "                // XHR for Chrome/Firefox/Opera/Safari/IE10+\n" +
            "                if ('withCredentials' in x) {\n" +
            "                    x.open('GET', u, true);\n" +
            "                }\n" +
            "                // XDomainRequest for IE8 & IE9\n" +
            "                else if (typeof XDomainRequest == 'function') {\n" +
            "                    x = new XDomainRequest();\n" +
            "                    x.open('GET', u);\n" +
            "                }\n" +
            "                else { return; }\n" +
            "                // Inject hidden div with sprite on load\n" +
            "                x.onload = function() {\n" +
            "                    var c = document.createElement('div');\n" +
            "                    c.setAttribute('hidden', '');\n" +
            "                    c.innerHTML = x.responseText;\n" +
            "                    b.insertBefore(c, b.childNodes[0]);\n" +
            "                }\n" +
            "                // Timeout for IE9\n" +
            "        \t\tsetTimeout(function () {\n" +
            "        \t\t\tx.send();\n" +
            "        \t\t}, 0);\n" +
            "            });\n" +
            "        })();\n" +
            "        </script>\n" +
            "\n" +
            "        <!-- Plyr core script -->\n" +
            "        <script src=\"../dist/plyr.js\"></script>\n" +
            "\n" +
            "        <!-- Shr core script -->\n" +
            "        <script src=\"https://cdn.shr.one/0.1.9/shr.js\"></script>\n" +
            "\n" +
            "        <!-- Docs script -->\n" +
            "        <script src=\"dist/docs.js\"></script>\n" +
            "    </body>\n" +
            "</html>";

    String intou = "<div class=\"plyr\">\n" +
            "    <div data-video-id=\"bTqVqk7FSmY\" data-type=\"youtube\"></div>\n" +
            "</div>";

    String iframe = "<iframe id=\"ytplayer\" type=\"text/html\" width=\"640\" height=\"390\"\n" +
            "  src=\"https://www.youtube.com/embed/M7lc1UVf-VE?autoplay=1&origin=http://example.com\"\n" +
            "  frameborder=\"0\"/>";

    String new1 = "<style>.embed-container { position: relative; padding-bottom: 56.25%; height: 0; overflow: hidden; max-width: 100%; } .embed-container iframe, .embed-container object, .embed-container embed { position: absolute; top: 0; left: 0; width: 100%; height: 100%; }</style><div class='embed-container'><iframe src='https://www.youtube.com/embed/QILiHiTD3uc' frameborder='0' allowfullscreen></iframe></div>";

    String in = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "  <body>\n" +
            "    <!-- 1. The <iframe> (and video player) will replace this <div> tag. -->\n" +
            "    <div id=\"player\"></div>\n" +
            "\n" +
            "    <script>\n" +
            "      // 2. This code loads the IFrame Player API code asynchronously.\n" +
            "      var tag = document.createElement('script');\n" +
            "\n" +
            "      tag.src = \"https://www.youtube.com/iframe_api\";\n" +
            "      var firstScriptTag = document.getElementsByTagName('script')[0];\n" +
            "      firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);\n" +
            "\n" +
            "      // 3. This function creates an <iframe> (and YouTube player)\n" +
            "      //    after the API code downloads.\n" +
            "      var player;\n" +
            "      function onYouTubeIframeAPIReady() {\n" +
            "        player = new YT.Player('player', {\n" +
            "          height: '390',\n" +
            "          width: '390',\n" +
            "          videoId: 'uO59tfQ2TbA',\n" +
            "          events: {\n" +
            "            'onReady': onPlayerReady,\n" +
            "            'onStateChange': onPlayerStateChange\n" +
            "          }\n" +
            "        });\n" +
            "      }\n" +
            "\n" +
            "      // 4. The API will call this function when the video player is ready.\n" +
            "      function onPlayerReady(event) {\n" +
            "        event.target.playVideo();\n" +
            "      }\n" +
            "\n" +
            "      // 5. The API calls this function when the player's state changes.\n" +
            "      //    The function indicates that when playing a video (state=1),\n" +
            "      //    the player should play for six seconds and then stop.\n" +
            "      var done = false;\n" +
            "      function onPlayerStateChange(event) {\n" +
            "        if (event.data == YT.PlayerState.PLAYING && !done) {\n" +
          //  "          setTimeout(stopVideo, 6000);\n" +
            "          done = true;\n" +
            "        }\n" +
            "      }\n" +
            "      function stopVideo() {\n" +
            "        player.stopVideo();\n" +
            "      }\n" +
            "    </script>\n" +
            "  </body>\n" +
            "</html>\n";

    @Override
    public void onCreate() {


        super.onCreate();



    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        //chatHead = new ImageView(this);

        // chatHead.setImageResource(R.drawable.floating);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                // WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);

        //  params.gravity = Gravity.TOP | Gravity.LEFT;
        //   params.x = 0;
        //   params.y = 100;

        LayoutInflater li = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View mTopView = li.inflate(R.layout.videoviewlayout, null);
        CordovaWebView wbb = (CordovaWebView) mTopView.findViewById(R.id.tutorialView);


       // WebSettings settings = wbb.getSettings();

       // settings.setJavaScriptEnabled(true);
       // wbb.getSettings().setJavaScriptEnabled(true);
       // wbb.getSettings().setDomStorageEnabled(true);

        // myWebView.setWebChromeClient(new MyChromwClient() );
      //  wbb.setWebChromeClient(new WebChromeClient());
      //  wbb.getSettings().setPluginState(WebSettings.PluginState.ON);
        //myWebView.setWebViewClient(new MyWebviewClient());
        //wbb.setWebViewClient(new WebViewClient());
      //  wbb.setWebViewClient((new WebViewClient() {
       //     @Override
       //     public boolean shouldOverrideUrlLoading(WebView view, String url) {
       //         return false;
       //     }
     //   }));
        // myWebView.addJavascriptInterface(new MyJavaScriptInterface(new));
        //  wbb.addJavascriptInterface(new MyJavaScriptInterface(this), "JSHandler");
        //     myWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //myWebView.addJavascriptInterface(new MyJavaScriptInterface(this), "JSHandler");
        //  webView.loadUrl("file:///android_asset/ytplayer.html");
        //myWebView.loadUrl(a);
        //myWebView.loadData(frameVideo, "text/html", "utf-8");
        // myWebView.loadDataWithBaseURL();
        // myWebView.loadDataWithBaseURL("https://www.youtube.com", youtubeHTML, "text/html; charset=utf-8", "UTF-8", null);
        //     StringBuilder sb;
        //      sb = new StringBuilder(32);
        //     sb.append("<html><body><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/");
        //      sb.append(mNum);


        //   sb.append("\" frameborder=\"0\" allowfullscreen></iframe></body></html>");
        // String nonyou;
        //  sb.toString(nonyou);
        //myWebView.loadDataWithBaseURL("https://www.youtube.com", youtubeHTML, "text/html; charset=utf-8", "UTF-8", null);
     //  wbb.loadDataWithBaseURL("https://www.youtube.com", new1, "text/html; charset=utf-8", "UTF-8", null);
        //    myWebView.loadDataWithBaseURL("https://www.youtube.com", n, "text/html; charset=utf-8", "UTF-8", null);
        // myWebView.loadDataWithBaseURL("https://www.youtube.com", html2, "text/html; charset=utf-8", "UTF-8", null);

        // myWebView.loadDataWithBaseURL("", html2, "text/html","UTF-8", "");
        //
        // myWebView.loadData(youtubeHTML1,"text/html; charset=utf-8", "UTF-8");
        // myWebView.loadUrl("https://www.youtube.com/embed/" + mNum);


        //View tv = v.findViewById(R.id.text);
        //((TextView)tv).setText("Dialog #" + mNum + ": using style "
        //      + getNameForNum(mNum));

        //     return v;


       // WebSettings wbset = wbb.getSettings();
      //  wbset.setJavaScriptEnabled(true);
       // wbb.setWebViewClient(new WebViewClient());
       // String url="http://www.google.com";
       String url1 = "https://www.youtube.com/watch?v=uO59tfQ2TbA";

        // System.out.println(getdeviceid());

       // wbb.getSettings().setJavaScriptEnabled(true);
        wbb.loadUrl(url1);

        // wbb.loadDataWithBaseURL("https://www.youtube.com", in, "text/html; charset=utf-8", "UTF-8", null);
        windowManager.addView(mTopView, params);

        return START_NOT_STICKY;

        // MediaCodec codec = null;
        //   try {
        //      codec = MediaCodec.createByCodecName("OMX.google.aac.encoder");
        //  } catch (IOException e) {
        //      e.printStackTrace();
        //   }
        // MediaCodec.createByCodecName("OMX.google.aac.encoder");
        //   MediaFormat format = new MediaFormat();
        //  format.setString(MediaFormat.KEY_MIME, "audio/mp4a-latm");
        //  format.setInteger(MediaFormat.KEY_AAC_PROFILE,
        //          MediaCodecInfo.CodecProfileLevel.AACObjectMain); //fixed version
        //   format.setInteger(MediaFormat.KEY_SAMPLE_RATE, nSamplerate);
        //   format.setInteger(MediaFormat.KEY_BIT_RATE, 128000);
        // format.setInteger(MediaFormat.KEY_CHANNEL_COUNT, nChannels);

        //codec.configure(format,);

        // codec.configure(format, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);
        // codec.start();

        //    ByteBuffer[] inputBuffers = codec.getInputBuffers();
        //    ByteBuffer[] outputBuffers = codec.getOutputBuffers();

        //   boolean bEndInput = false;
        //   boolean bEndOutput = false;

        //  while(true)
        //  {
        //     if (!bEndInput)
        //     {
        //        int inputBufferIndex = codec.dequeueInputBuffer(0);
        //        if (inputBufferIndex >= 0)
        //        {
        // int nLen = app.readPCM(nHandle,inputBuffers[inputBufferIndex]);//This line read PCM, return 0 if end of data.
        //          int nBufLen = inputBuffers[inputBufferIndex].capacity();
        //  if (nLen == nBufLen)
        //      codec.queueInputBuffer(inputBufferIndex, 0, nLen, 0,  MediaCodec.BUFFER_FLAG_SYNC_FRAME);
        //  else if (nLen < nBufLen)
        //       {
        //    codec.queueInputBuffer(inputBufferIndex, 0, nLen, 0,  MediaCodec.BUFFER_FLAG_END_OF_STREAM);
        //          bEndInput = true;
        //         break;
        //      }
        //    }
        //    }

        //     MediaCodec.BufferInfo info = new MediaCodec.BufferInfo();
        //     if (!bEndOutput)
        //    {
        //       int outputBufferIndex = codec.dequeueOutputBuffer(info, 0);
        //       if (outputBufferIndex  >= 0)
         /*       {
                    int outBitsSize   = info.size;
                    Log.d("test", "Offset:"+info.offset);
                    Log.d("test", "Size:"+info.size);
                    Log.d("test", "Time:"+info.presentationTimeUs);
                    Log.d("test", "Flags:"+info.flags);
                    if (info.flags != 0) //fixed version
                    {
           //             codec.releaseOutputBuffer(outputBufferIndex, false /* render *///);
          /*              continue;
                    }

                    int outPacketSize = outBitsSize + 7;    // 7 is ADTS size
                    ByteBuffer outBuf = outputBuffers[outputBufferIndex];

                    outBuf.position(info.offset);
                    outBuf.limit(info.offset + outBitsSize);
                  //  try {
                        byte[] data = new byte[outPacketSize];  //space for ADTS header included

                     //   addADTStoPacket(data, outPacketSize);
                        outBuf.get(data, 7, outBitsSize);
                        outBuf.position(info.offset);
                  //      outputStream.write(data, 0, outPacketSize);  //open FileOutputStream beforehand
                // } catch
                    //(IOException e)
                  //   {
                  //      Log.e("test", "failed writing bitstream data to file");
                  //      e.printStackTrace();
                   // }

                    outBuf.clear();
                    codec.releaseOutputBuffer(outputBufferIndex, false /* render *///);
        //        Log.d("test", "  dequeued " + outBitsSize + " bytes of output data.");
        //         Log.d("test", "  wrote " + outPacketSize + " bytes into output file.");
//
        //          if (info.flags == MediaCodec.BUFFER_FLAG_END_OF_STREAM)
        //         {
        //              bEndOutput = true;
        //break;
        //       }
        //    }
        //     else if (outputBufferIndex == MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED)
        //     {
        //           outputBuffers = codec.getOutputBuffers();
        //    }
        //       else if (outputBufferIndex == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED)
        //      {

        //      }
        //    }

        //   if (bEndInput && bEndOutput)
        //       break;

        // final EMVideoView emVideoView = (EMVideoView)mTopView.findViewById(R.id.video_play_activity_video_view);
        //emVideoView.setVideoURI(Uri.parse("https://archive.org/download/Popeye_forPresident/Popeye_forPresident_512kb.mp4"));
        // emVideoView.setVideoURI(Uri.parse("http://www.youtube.com/api/manifest/dash/id/bf5bb2419360daf1/source/youtube?\"\n" +
        //        "        + \"as=fmp4_audio_clear,fmp4_sd_hd_clear&sparams=ip,ipbits,expire,as&ip=0.0.0.0&\"\n" +
        //         "        + \"ipbits=0&expire=19000000000&signature=255F6B3C07C753C88708C07EA31B7A1A10703C8D.\"\n" +
        //        "        + \"2D6A28B21F921D0B245CDCF36F7EB54A2B5ABFC2&key=ik0\""));


        // VideoView videoView =
        //         (VideoView) mTopView.findViewById(R.id.videoview);
        // String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
        // Uri vidUri = Uri.parse(vidAddress);
        // videoView.setVideoURI(vidUri);


        //videoView.start();
        // emVideoView.start();


        //  }

  /*  @Override
    public void onCreate() {
        super.onCreate();

        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        //chatHead = new ImageView(this);

       // chatHead.setImageResource(R.drawable.floating);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
               // WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);

      //  params.gravity = Gravity.TOP | Gravity.LEFT;
     //   params.x = 0;
     //   params.y = 100;

        LayoutInflater li = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
       View mTopView =  li.inflate(R.layout.exoplayertest, null);
        final EMVideoView emVideoView = (EMVideoView)mTopView.findViewById(R.id.video_play_activity_video_view);
        //emVideoView.setVideoURI(Uri.parse("https://archive.org/download/Popeye_forPresident/Popeye_forPresident_512kb.mp4"));
        emVideoView.setVideoURI(Uri.parse("https://www.youtube.com/watch?v=xHUIbFNO0II"));


        // VideoView videoView =
       //         (VideoView) mTopView.findViewById(R.id.videoview);
       // String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
       // Uri vidUri = Uri.parse(vidAddress);
       // videoView.setVideoURI(vidUri);


        windowManager.addView(mTopView, params);
        //videoView.start();
        emVideoView.start();
       // MediaController vidControl = new MediaController(this);

   */// }

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
