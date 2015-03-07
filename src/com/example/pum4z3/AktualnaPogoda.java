package com.example.pum4z3;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AktualnaPogoda
{
	private String name, sunRise, sunSet,precipitation,weatherValue, windDirection,icon;
	private double temperature,humidity,pressure,windSpeed;
	
	public String getIcon()
	{
		return icon;
	}
	public void setIcon(String icon)
	{
		this.icon = icon;
	}
	public String getWindDirection()
	{
		return windDirection;
	}
	public void setWindDirection(String windDirection)
	{
		this.windDirection = windDirection;
	}	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getSunRise()
	{
		return sunRise;
	}
	public void setSunRise(String sunRise)
	{
		this.sunRise = sunRise;
	}
	public String getSunSet()
	{
		return sunSet;
	}
	public void setSunSet(String sunSet)
	{
		this.sunSet = sunSet;
	}

	public String getPrecipitation()
	{
		return precipitation;
	}
	public void setPrecipitation(String precipitation)
	{
		this.precipitation = precipitation;
	}
	public String getWeatherValue()
	{
		return weatherValue;
	}
	public void setWeatherValue(String weatherValue)
	{
		this.weatherValue = weatherValue;
	}
	public double getTemperature()
	{
		return temperature;
	}
	public void setTemperature(double temperature)
	{
		temperature = temperature - 273.15;
		temperature *= 100;
		temperature = Math.round(temperature);
		temperature /= 100;
		this.temperature = temperature;
	}
	public double getHumidity()
	{
		return humidity;
	}
	public void setHumidity(double humidity)
	{
		this.humidity = humidity;
	}
	public double getPressure()
	{
		return pressure;
	}
	public void setPressure(double pressure)
	{
		this.pressure = pressure;
	}
	public double getWindSpeed()
	{
		return windSpeed;
	}
	public void setWindSpeed(double windSpeed)
	{
		this.windSpeed = windSpeed;
	}
	
	public void ustawAnimacje(int idIcona, final ImageView obrazek)
	{	
		
		obrazek.setBackgroundResource(idIcona);
		obrazek.post(new Runnable() 
        {
        	@Override
        	public void run()
        	{
        		AnimationDrawable Animacja = (AnimationDrawable) obrazek.getBackground();
        		Animacja.start();
        	}
        }
         
    );
	}
	
	
	public void wyswietlDanePogodowe(Activity activity)
	{
		TextView txtLokalizacja = (TextView) activity.findViewById(R.id.textViewNearestLoc);
		TextView txtOpis = (TextView) activity.findViewById(R.id.textViewOpis);
		TextView txtTemperatura = (TextView) activity.findViewById(R.id.textViewTemperatura);
		TextView txtCisnienie = (TextView) activity.findViewById(R.id.textViewCisnienie);
		TextView txtWilgotnosc = (TextView) activity.findViewById(R.id.textViewWilgotnosc);
		TextView txtPrzewidywaneOpady = (TextView) activity.findViewById(R.id.textViewPrzewidywaneOpady);
		TextView txtWiatrPredkosc = (TextView) activity.findViewById(R.id.textViewWiatrPredkosc);
		TextView txtWiatrKierunek = (TextView) activity.findViewById(R.id.textViewWiatrKierunek);
		TextView txtWschod = (TextView) activity.findViewById(R.id.textViewWschod);
		TextView txtZachod = (TextView) activity.findViewById(R.id.textViewZachod);
		
		ImageView obrazek2 = (ImageView) activity.findViewById(R.id.imageViewObrazekpreloader);
		obrazek2.setVisibility(View.GONE);
		
		ImageView obrazek = (ImageView) activity.findViewById(R.id.imageViewObrazek);
		obrazek.setVisibility(View.VISIBLE);
		

		txtLokalizacja.setText(getName());
		txtOpis.setText(getWeatherValue());
		txtTemperatura.setText(String.valueOf(getTemperature()));
		txtCisnienie.setText(String.valueOf(getPressure()));
		txtWilgotnosc.setText(String.valueOf(getHumidity()));
		txtPrzewidywaneOpady.setText(getPrecipitation());
		txtWiatrPredkosc.setText(String.valueOf(getWindSpeed()));
		txtWiatrKierunek.setText(getWindDirection());
		txtWschod.setText(getSunRise());
		txtZachod.setText(getSunSet());
		
		switch (getIcon())
		{
		case "01d": { obrazek.clearAnimation(); obrazek.setBackgroundResource(R.drawable.foto_02d1); ustawAnimacje(R.anim.slonce, obrazek); } break;
		case "02d": { obrazek.clearAnimation(); obrazek.setBackgroundResource(R.drawable.foto_02d1); ustawAnimacje(R.anim.anim_02d, obrazek); } break;
		case "03d": { obrazek.clearAnimation(); obrazek.setBackgroundResource(R.drawable.foto_03d1); ustawAnimacje(R.anim.anim_03d, obrazek); } break;
		case "04d": { obrazek.clearAnimation(); obrazek.setBackgroundResource(R.drawable.foto_04d1); ustawAnimacje(R.anim.anim_04d, obrazek); } break;
		case "09d": { obrazek.clearAnimation(); obrazek.setBackgroundResource(R.drawable.foto_09d1); ustawAnimacje(R.anim.anim_09d, obrazek); } break;
		case "10d": { obrazek.clearAnimation(); obrazek.setBackgroundResource(R.drawable.foto_10d1); ustawAnimacje(R.anim.anim_10d, obrazek); } break;
		case "11d": { obrazek.clearAnimation(); obrazek.setBackgroundResource(R.drawable.foto_11d1); ustawAnimacje(R.anim.anim_11d, obrazek); } break;
		case "13d": { obrazek.clearAnimation(); obrazek.setBackgroundResource(R.drawable.foto_13d1); ustawAnimacje(R.anim.anim_13d, obrazek); } break;
		case "50d": { obrazek.clearAnimation(); obrazek.setBackgroundResource(R.drawable.foto_50d1); ustawAnimacje(R.anim.anim_50d, obrazek); } break;

		case "01n": { obrazek.clearAnimation(); obrazek.setBackgroundResource(R.drawable.foto_01n1); ustawAnimacje(R.anim.anim_01n, obrazek); } break; 
		case "02n": { obrazek.clearAnimation(); obrazek.setBackgroundResource(R.drawable.foto_02n1); ustawAnimacje(R.anim.anim_02n, obrazek); } break;
		case "03n": { obrazek.clearAnimation(); obrazek.setBackgroundResource(R.drawable.foto_03d1); ustawAnimacje(R.anim.anim_03d, obrazek); } break;
		case "04n": { obrazek.clearAnimation(); obrazek.setBackgroundResource(R.drawable.foto_04d1); ustawAnimacje(R.anim.anim_04d, obrazek); } break;
		case "09n": { obrazek.clearAnimation(); obrazek.setBackgroundResource(R.drawable.foto_09d1); ustawAnimacje(R.anim.anim_09d, obrazek); } break;
		case "10n": { obrazek.clearAnimation(); obrazek.setBackgroundResource(R.drawable.foto_10n1); ustawAnimacje(R.anim.anim_10n, obrazek); } break;
		case "11n": { obrazek.clearAnimation(); obrazek.setBackgroundResource(R.drawable.foto_11d1); ustawAnimacje(R.anim.anim_11d, obrazek); } break;
		case "13n": { obrazek.clearAnimation(); obrazek.setBackgroundResource(R.drawable.foto_13d1); ustawAnimacje(R.anim.anim_13d, obrazek); } break;
		case "50n": { obrazek.clearAnimation(); obrazek.setBackgroundResource(R.drawable.foto_50d1); ustawAnimacje(R.anim.anim_50d, obrazek); } break;
			

		default:
			break;
		}
		}
	
//	  <city id="3096053" name="K³odzko"> ---
//    <coord lon="16.66" lat="50.44"/> 
//    <country>Poland</country>
//    <sun rise="2014-12-13T06:45:53" set="2014-12-13T14:49:33"/>
//  </city>
//  <temperature value="279.097" min="279.097" max="279.097" unit="kelvin"/>
//  <humidity value="76" unit="%"/>
//  <pressure value="964.22" unit="hPa"/>
//  <wind>
//    <speed value="4.89" name="Gentle Breeze"/>
//    <direction value="214.003" code="SW" name="Southwest"/>
//  </wind>
//  <clouds value="8" name="clear sky"/>
//  <visibility/>
//  <precipitation mode="no"/>
//  <weather number="800" value="sky is clear" icon="02d"/>
//  <lastupdate value="2014-12-13T11:21:53"/>
	

}
