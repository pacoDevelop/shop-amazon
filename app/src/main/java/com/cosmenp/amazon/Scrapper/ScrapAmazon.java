package com.cosmenp.amazon.Scrapper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.cosmenp.amazon.MainActivity;
import com.cosmenp.amazon.R;
import com.cosmenp.amazon.db.AppDatabase;
import com.cosmenp.amazon.db.AppDatabaseSingleton;
import com.cosmenp.amazon.entity.Producto;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;


public class ScrapAmazon {
    static String htmla;
    private static Producto temp;

    static class MyJavaScriptInterface {

        private Context ctx;
        public String html;
        private TextView banner;


        MyJavaScriptInterface(Context ctx,TextView banner) {

            this.ctx = ctx;
            this.banner=banner;
        }

        @JavascriptInterface
        public void showHTML(String html) {
            if(html.contains("priceblock_ourprice")) {
                parseHtml(html,ctx);

                if(temp!=null) {
                    AppDatabase  db = AppDatabaseSingleton.getInstance(ctx).getAppDatabase();
                    if(db.productosDAO().get(temp.getIdProducto(),temp.getPrecioCoste())==0) {
                        db.productosDAO().add(temp);

                        banner.setText("Insertado correctamente");
                    }else{
                        banner.setText("Ya existe el producto");
                    }
                }else{
                    banner.setText("No se ha podido insertar");
                }
            }else{
                Toast.makeText(ctx,"No es un enlace valido",Toast.LENGTH_LONG).show();
            }
            this.html = html;
        }
    }

    @SuppressLint("JavascriptInterface")
    public static void populateProducto(final String urla, Context context, final TextView textView) {
        temp=null;

        final WebView webView= ((Activity)context).findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36");
        webView.setVisibility(View.INVISIBLE);
        final MyJavaScriptInterface jInterface = new MyJavaScriptInterface(context,textView);
        webView.addJavascriptInterface(jInterface, "HtmlViewer");
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {

                //Load HTML

                view.loadUrl("javascript:window.HtmlViewer.showHTML(document.getElementById('ppd').innerHTML);");
                //Saca all el html document.documentElement.outerHTML

                super.onPageFinished(view,url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                if (url.contains("captcha")) {
                    webView.loadUrl(urla);
                }
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                textView.setText("Error de url o conexion");

            }
        });

        webView.loadUrl(urla);
    }

    private static void parseHtml(final String html,Context context){
    temp=null;
        Log.d("datos","a");
                            if(html!=null) {
                                Elements lista;
                                try {

                                    Document doc = Jsoup.parse(html);
                                    String urlImg,nombre,precioCosteString,descripcion="";
                                    double precioCoste,pvp;

                                   // Log.d("datosa",doc.getElementById("imgTagWrapperId").getElementsByTag("img").attr("src"));
                                    urlImg=doc.getElementById("imgTagWrapperId").getElementsByTag("img").attr("src");
                                    nombre=doc.getElementById("productTitle").html().trim();
                                    precioCosteString=doc.getElementById("priceblock_ourprice").html().replace("&nbsp;€","");
                                    if (precioCosteString.contains(",")) {
                                        precioCosteString=precioCosteString.replace(",",".").trim();
                                    }
                                    precioCoste=Double.parseDouble(precioCosteString);
                                    lista=doc.getElementById("feature-bullets").getElementsByClass("a-list-item");

                                    for (Element li :
                                           lista ) {
                                        if (!li.html().contains("<span>")) {
                                            descripcion += li.html().trim() + "\n";
                                        }
                                    }
                                    pvp=precioCoste+3;

                                    temp=new Producto(nombre,precioCoste,pvp,descripcion,urlImg);
                                    Log.d("Datos",temp.toString());


                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }



            }

    public static Producto getTemp() {
        return temp;
    }
}



