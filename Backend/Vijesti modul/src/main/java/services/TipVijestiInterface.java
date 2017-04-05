package services;

import java.util.List;

import com.fitandsit.Model.tipvijesti;

import jsonwrappers.tipvijestiJSONWrapper;

public interface TipVijestiInterface {
	tipvijestiJSONWrapper createtipvijesti(tipvijesti tv);
	tipvijestiJSONWrapper gettipvijesti(Long id);
	tipvijestiJSONWrapper updatetipvijesti(Long id, tipvijesti tv);
	Boolean deletetipvijesti(Long id);
	List<tipvijesti> gettipvijestii();
}
