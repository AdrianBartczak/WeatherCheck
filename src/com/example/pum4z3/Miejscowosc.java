package com.example.pum4z3;

public class Miejscowosc
{
	private double wspX, wspY, odleglosc;
	private String nazwa,zakodowaneDane;
	
	
	public double getOdleglosc()
	{
		return odleglosc;
	}
	public void setOdleglosc(double odleglosc)
	{
		this.odleglosc = odleglosc;
	}
	public double getWspX()
	{
		return wspX;
	}
	public void setWspX(double wspX)
	{
		this.wspX = wspX;
	}
	public double getWspY()
	{
		return wspY;
	}
	public void setWspY(double wspY)
	{
		this.wspY = wspY;
	}
	public String getNazwa()
	{
		return nazwa;
	}
	public void setNazwa(String nazwa)
	{
		this.nazwa = nazwa;
	}
	public String getZakodowaneDane()
	{
		return zakodowaneDane;
	}
	public void setZakodowaneDane(String zakodowaneDane)
	{
		this.zakodowaneDane = zakodowaneDane;
	}
	
	public void dekodujDane()
	{
//														    63				79
//		<tr><td><font face="Arial" COLOR="c0c0c0" size="2"><b><a name="A"></a>Aleksandrów Kujawski</b></font></td><td><font face="Arial" COLOR="505050" size="2"><b>52.880 &deg;N&nbsp;&nbsp;</b></font></td><td><font face="Arial" COLOR="505050" size="2"><b>18.700 &deg;E</b></font></td><td width=30></td><td><font face="Arial" COLOR="404040" size="2"><b>Kujawsko-Pomorskie</td></tr>
//		<tr><td><font face="Arial" COLOR="c0c0c0" size="2"><b>Aleksandrów £ódzki</b></font></td><td><font face="Arial" COLOR="505050" size="2"><b>51.820 &deg;N&nbsp;&nbsp;</b></font></td><td><font face="Arial" COLOR="505050" size="2"><b>19.299 &deg;E</b></font></td><td width=30></td><td><font face="Arial" COLOR="404040" size="2"><b>£ódzkie</td></tr>
		StringBuilder stringBuilder = new StringBuilder(getZakodowaneDane());
		
		stringBuilder.delete(0, 54);
		if (stringBuilder.charAt(0)=='<')
		{
			stringBuilder.delete(0, 16); // wyjatek <a name="A">
		}
		int i = 0;

		setNazwa("");
		while (stringBuilder.charAt(i)!='<')
		{
			nazwa = nazwa + stringBuilder.charAt(i);
			i++;		
		}
		stringBuilder.delete(0, i); //delete nazwy
		stringBuilder.delete(0, 66); // delete
		
		String strWspY = "";
		
		for (int j = 0; j < 6; j++)
		{
			strWspY = strWspY + stringBuilder.charAt(j);
		}
		
		wspY = Double.parseDouble(strWspY);
		
		stringBuilder.delete(0, 6);
		stringBuilder.delete(0, 85);
		
		String strWspX = "";
		
		for (int j = 0; j < 6; j++)
		{
			strWspX = strWspX + stringBuilder.charAt(j);
		}
		
		wspX = Double.parseDouble(strWspX);
		stringBuilder.delete(0, stringBuilder.length());
		
		setZakodowaneDane(stringBuilder.toString());
	}
	

}
