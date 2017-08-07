package com.example.max241.fragmentunitest.Music;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.max241.fragmentunitest.AppView;
import com.example.max241.fragmentunitest.Event;
import com.example.max241.fragmentunitest.HttpConnect;
import com.example.max241.fragmentunitest.Info;
import com.example.max241.fragmentunitest.Port;
import com.example.max241.fragmentunitest.resourceBinding;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Response extends Port {
	
	Info info  = new Info();

	String url;
	String joperacompositeservice;

	//==================此區宣告將會使用到的元件==================//
	TextView tv;
	AppView appview;
	EditText et;
	Context context;
	//============================================================//




	public Response()
	{

	}



	public Response(AppView view,Info info)
	{

		this.appview=appview;
		this.info.setId(info.getId());
		this.info.setType(info.getType());
		
	}

	public void setElement(EditText et,Context context)
	{
		this.et=et;
		this.context=context;

	}
	
	@Override
	public void init(resourceBinding data) {
		
			this.url=data.getDefaultResourceUrl();
			this.joperacompositeservice="";

	}

	
	
	
	//===================================設計此Event接收到後 與Service Mediator或是與RESTful Service的溝通 以及呈現元件的設值==================================//
	@Override
	public void inputEventPort(Event event) {
		
		HttpConnect HC=new HttpConnect();
			
		String url;

		String result="";
		try {
			url = this.url+"?_singername="+URLEncoder.encode(event.getContent(), "UTF-8");

			System.out.println(url);

			System.out.print("Event Response:"+HC.getData(url));

			CharSequence text1 = "Event Response";      

			int duration1 = Toast.LENGTH_SHORT;   

			final Toast toast1 = Toast.makeText(context, text1, duration1); 
			toast1.show();

			String jsonStr = HC.getData(url);


			jsonStr=jsonStr.replaceAll("\\\\", "");



			jsonStr=jsonStr.replace("\"{", "{");
			jsonStr=jsonStr.replace("}\"", "}");

			System.out.println(jsonStr);

			JSONParser parser = new JSONParser();

			Object obj = parser.parse(jsonStr);
			JSONObject jsonObject =  (JSONObject) obj;

			System.out.println(jsonObject.toJSONString());

			if(jsonObject.get("_albums").equals("搜尋不到結果"))
			{
				result="搜尋不到結果";

			}
			else {

				JSONObject albums = (JSONObject) jsonObject.get("_albums");


				JSONArray albumsarr = (JSONArray) albums.get("albums");


				result += "album array:\n";

				System.out.print("album array:\n");

				for (int j = 0; j < albumsarr.size(); j++) {

					if(j!=albumsarr.size()-1) {
						result += albumsarr.get(j) + "\n";
						System.out.println(albumsarr.get(j));
					}
					else
					{
						result += albumsarr.get(j);
					}

				}



			}

			et.setText(result);										//呈現元件的設值
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	//==========================================================================================================//




	public AppView getAppView() {
		return appview;
	}

	public void setAppView(AppView appView) {
		this.appview = appView;
	}

	public Info getInfo() {
		return info;
	}


	public void setInfo(Info info) {
		this.info = info;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getJoperacompositeservice() {
		return joperacompositeservice;
	}



	public void setJoperacompositeservice(String joperacompositeservice) {
		this.joperacompositeservice = joperacompositeservice;
	}


	
	
	
	
}
