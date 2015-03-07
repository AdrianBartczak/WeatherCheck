package com.example.pum4z3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity
{
	private TextView labelWspX, labelWspY, labelNearestLoc;
	private double aktualneWspX, aktualneWspY;
	private List<Miejscowosc> listaMiejscowosci = new ArrayList<Miejscowosc>();
	private Activity activity = this;
	private boolean czyPogodaJestUstawiona = false;
	//private AktualnaPogoda aktualnaPogoda;
	private String znalezionaNajblizszaLokalizacja;
	
	public String getZnalezionaNajblizszaLokalizacja()
	{
		return znalezionaNajblizszaLokalizacja;
	}
	public void setZnalezionaNajblizszaLokalizacja(
			String znalezionaNajblizszaLokalizacja)
	{
		this.znalezionaNajblizszaLokalizacja = znalezionaNajblizszaLokalizacja;
	}
	public double getAktualneWspX()
	{
		return aktualneWspX;
	}
	public void setAktualneWspX(double aktualneWspX)
	{
		this.aktualneWspX = aktualneWspX;
	}
	public double getAktualneWspY()
	{
		return aktualneWspY;
	}
	public void setAktualneWspY(double aktualneWspY)
	{
		this.aktualneWspY = aktualneWspY;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		labelWspX = (TextView) findViewById(R.id.textViewWspX);
		labelWspY = (TextView) findViewById(R.id.textViewWspY);
		labelNearestLoc = (TextView) findViewById(R.id.textViewNearestLoc);
		
		// wspX: 16,659  wspY: 50,439
		
//		ImageView obrazek2 = (ImageView) findViewById(R.id.imageViewObrazek);
//		obrazek2.setVisibility(View.GONE);
		
//		final ImageView imageIntro = (ImageView) findViewById(R.id.imageViewObrazekpreloader);
//		
//		imageIntro.setBackgroundResource(R.anim.preloader);
//		imageIntro.post(new Runnable() 
//        {
//        	@Override
//        	public void run()
//        	{
//        		AnimationDrawable Animacja = (AnimationDrawable) imageIntro.getBackground();
//        		Animacja.start();
//        	}
//        }
//         
//		);
		
		
//		final ImageView imageIntro2 = (ImageView) findViewById(R.id.imageViewObrazek);
//		
//		imageIntro2.setBackgroundResource(R.anim.anim_01n);
//		imageIntro2.post(new Runnable() 
//        {
//        	@Override
//        	public void run()
//        	{
//        		AnimationDrawable Animacja = (AnimationDrawable) imageIntro.getBackground();
//        		Animacja.start();
//        	}
//        }
//         
//    );
		
		
		wlaczLocationListener();

		
	}

	
	public void wlaczLocationListener()
	{
		LocationManager menadzerLokalizacji = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		
		LocationListener nasluchiwaczLokalizacji = new LocationListener()
		{
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderEnabled(String provider)
			{
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onProviderDisabled(String provider)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLocationChanged(Location location)
			{
//				setAktualneWspX(location.getLongitude()); //pobieramy d³ugoœæ geograficzn¹ wspY
//				setAktualneWspY(location.getLatitude()); //pobieramy szerokoœæ geograficzn¹ wspX

//				labelWspX = (TextView) findViewById(R.id.textViewWspX);
//				labelWspY = (TextView) findViewById(R.id.textViewWspY);
							
				labelWspX.setText(String.valueOf(location.getLongitude()));
				labelWspY.setText(String.valueOf(location.getLatitude()));
				setAktualneWspX(location.getLongitude());
				setAktualneWspY(location.getLatitude());
				
				if (!czyPogodaJestUstawiona) {
					WatekPobieraniaDanychCybermoon cybermoon = new WatekPobieraniaDanychCybermoon();
					cybermoon.execute(null,null,null);	
					
				}

				
			}
		};
		
		menadzerLokalizacji.requestLocationUpdates(menadzerLokalizacji.GPS_PROVIDER, 0, 0, nasluchiwaczLokalizacji);
	}
	
	public void findNearestLocation()
	{
		
//		setAktualneWspX(Double.parseDouble(labelWspX.getText().toString()));
//		setAktualneWspY(Double.parseDouble(labelWspY.getText().toString()));
		
		Miejscowosc pasujacaMiejscowosc = null;		
		for ( Miejscowosc miejscowosc : listaMiejscowosci )
		{		
			double kwadratx = (double) (getAktualneWspX() - miejscowosc.getWspX());			
			kwadratx = kwadratx * kwadratx;			
			double kwadraty = (double) (getAktualneWspY() - miejscowosc.getWspY());		
			kwadraty = kwadraty * kwadraty;
			
			double odleglosc = Math.sqrt(kwadratx+kwadraty);
			miejscowosc.setOdleglosc(odleglosc);
			
			if (pasujacaMiejscowosc == null) { pasujacaMiejscowosc = miejscowosc; }
			else if (miejscowosc.getOdleglosc()<pasujacaMiejscowosc.getOdleglosc())
			{
				pasujacaMiejscowosc = miejscowosc;
			}
		}
		
//		labelNearestLoc.setText(pasujacaMiejscowosc.getNazwa());
		setZnalezionaNajblizszaLokalizacja(pasujacaMiejscowosc.getNazwa());
		
	}
	
	public class WatekPobieraniaDanychCybermoon extends AsyncTask<Void, Void, Void>{
		
		//private String wynik;
		protected Void doInBackground(Void... JakiesParametry) {
			try {	
				listaMiejscowosci.clear();
				URL ObiektURL = new URL("http","cybermoon.pl",80,"/wiedza/wspolrzedne_polskich_miast.html");
				URLConnection Polaczenie = ObiektURL.openConnection();
				Polaczenie.setDoInput(true);
				Polaczenie.connect();
				BufferedReader obslugaBufora = new BufferedReader(new InputStreamReader(Polaczenie.getInputStream(),"ISO-8859-2"));
				
				String wiersz;
				int licznik=0;
				while((wiersz=obslugaBufora.readLine())!=null){

					licznik++;
					if (licznik>192 && licznik<632)
					{
						Miejscowosc miejscowosc = new Miejscowosc();
						miejscowosc.setZakodowaneDane(wiersz);
						miejscowosc.dekodujDane();
						listaMiejscowosci.add(miejscowosc);
						//Log.d("DEKODUJE",miejscowosc.getNazwa()+" wspY="+miejscowosc.getWspY()+" wspX="+miejscowosc.getWspX());
					}
					
					
				}
				obslugaBufora.close();

				//
				
				//Log.d("WEB",listaMiejscowosci.get(1).getNazwa());
				//wynik = GeneratorNapisu.toString();
				//textWynik.setText(GeneratorNapisu.toString());
				

				
			} catch (Exception e) { 
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
		    super.onPostExecute(result);
			
		    findNearestLocation();
	    	WatekPobieraniaDanychPogodowychXML w = new WatekPobieraniaDanychPogodowychXML();
			w.execute(null, null, null);
		}
		}
	
	public class WatekPobieraniaDanychPogodowychXML extends AsyncTask<Void, Void, Void> {

		private AktualnaPogoda aktualnaPogoda;

		private String dawajDane(String adres, int port, String zasob) {
			String x = null;
			HttpHost server = new HttpHost(adres, port);
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(zasob);
			HttpEntity wynik = null;
			try {
				HttpResponse odpowiedz = client.execute(server, get);
				wynik = odpowiedz.getEntity();
				x = EntityUtils.toString(wynik);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return x;
		}

		public Document dawajXml(String adres, int port,String zasob) {
			Document d = null;
			try {
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				InputSource is = new InputSource();
				String string = dawajDane(adres, port, zasob);
				is.setCharacterStream(new StringReader(string));
				d = db.parse(is);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return d;

		}
		
		private String dajWartosc(String tag, Element element) {
			NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
			Node node = (Node) nodeList.item(0);
			return node.getNodeValue();
			}
	
		
		private Element dajAtrybut(String tagName, Document xml) {
		      NodeList listaDanych = xml.getElementsByTagName(tagName);
		      Node wezel = listaDanych.item(0);
		      Element element = (Element)wezel;
		      return element;

		}

		
		@Override
		protected Void doInBackground(Void... params) {
			// http://api.openweathermap.org/data/2.5/weather?q=K%C5%82odzko&mode=xml
			String s=dawajDane("api.openweathermap.org",80, "/data/2.5/weather?q="+getZnalezionaNajblizszaLokalizacja()+"&mode=xml");
			Log.d("POGODA DOC",s);
			
			try {

				//Document doc = dawajXml("api.openweathermap.org",80, "/data/2.5/weather?q=K%C5%82odzko&mode=xml");
				Document doc = dawajXml("api.openweathermap.org",80, "/data/2.5/weather?q="+getZnalezionaNajblizszaLokalizacja()+"&mode=xml");
				Element element=doc.getDocumentElement();
				element.normalize();
				
				AktualnaPogoda aktualnaPogoda = new AktualnaPogoda();
				
				Element elementWyb = dajAtrybut("city",doc);
				aktualnaPogoda.setName(elementWyb.getAttribute("name"));
				
				elementWyb = dajAtrybut("sun",doc);
				aktualnaPogoda.setSunRise(elementWyb.getAttribute("rise"));
				
				elementWyb = dajAtrybut("sun",doc);
				aktualnaPogoda.setSunSet(elementWyb.getAttribute("set"));
				
				elementWyb = dajAtrybut("weather",doc);
				aktualnaPogoda.setWeatherValue(elementWyb.getAttribute("value"));				
				
				elementWyb = dajAtrybut("temperature",doc);
				aktualnaPogoda.setTemperature(Double.parseDouble(elementWyb.getAttribute("value")));
				
				elementWyb = dajAtrybut("humidity",doc);
				aktualnaPogoda.setHumidity(Double.parseDouble(elementWyb.getAttribute("value")));
				
				elementWyb = dajAtrybut("pressure", doc);
				aktualnaPogoda.setPressure(Double.parseDouble(elementWyb.getAttribute("value")));
				
				elementWyb = dajAtrybut("speed", doc);
				aktualnaPogoda.setWindSpeed(Double.parseDouble(elementWyb.getAttribute("value")));
				
				elementWyb = dajAtrybut("direction", doc);
				aktualnaPogoda.setWindDirection(elementWyb.getAttribute("name"));
				
				elementWyb = dajAtrybut("precipitation", doc);
				aktualnaPogoda.setPrecipitation(elementWyb.getAttribute("mode"));
				
				elementWyb = dajAtrybut("weather", doc);
				aktualnaPogoda.setIcon(elementWyb.getAttribute("icon"));
				this.aktualnaPogoda = aktualnaPogoda;
				
				 Log.d("akt Miejscowosc",aktualnaPogoda.getName());
				 Log.d("akt TypPogody",aktualnaPogoda.getWeatherValue());
				 Log.d("akt teperatura",String.valueOf(aktualnaPogoda.getTemperature()));
				 Log.d("akt cisnienie",String.valueOf(aktualnaPogoda.getPressure()));
				 Log.d("akt wilgotnosc",String.valueOf(aktualnaPogoda.getHumidity()));
				 Log.d("akt wiatr speed",String.valueOf(aktualnaPogoda.getWindSpeed()));
				 Log.d("akt wiatr kier",aktualnaPogoda.getWindDirection());
				 Log.d("akt icon",aktualnaPogoda.getIcon());
				 Log.d("akt wschod",aktualnaPogoda.getSunRise());
				 Log.d("akt zachod",aktualnaPogoda.getSunSet());
	
			} catch (Exception e) {e.printStackTrace();}

			
			
			return null;

		}
		
		@Override
		protected void onPostExecute(Void result) {
		    super.onPostExecute(result);
		    
		    aktualnaPogoda.wyswietlDanePogodowe(activity);
		    

		}
	}
	
}
