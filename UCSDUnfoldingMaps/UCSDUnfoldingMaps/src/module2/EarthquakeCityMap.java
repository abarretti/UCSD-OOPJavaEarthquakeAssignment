package module2;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class EarthquakeCityMap extends PApplet {
	
	private UnfoldingMap map;
	
	public void setup() {
		size(950, 600, OPENGL);
		map = new UnfoldingMap(this, 125, 50, 700, 500, new Google.GoogleMapProvider());
		map.zoomToLevel(2);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		Location valLoc = new Location(-38.14f, -73.03f);
		Feature valEq = new PointFeature(valLoc);
		valEq.addProperty("title", "Valdivia, Chile");
		valEq.addProperty("magnitude", "9.5");
		valEq.addProperty("date", "May 22, 1960");
		valEq.addProperty("year", "1960");
		
		List<PointFeature> bigEqs = new ArrayList<PointFeature>();
		bigEqs.add((PointFeature) valEq);
//		bigEqs.add((PointFeature) alaskaEq);
//		bigEqs.add((PointFeature) sumatraEq);
//		bigEqs.add((PointFeature) japanEq);
//		bigEqs.add((PointFeature) kamchatkaEq);
		
		List<Marker> markers = new ArrayList<Marker>();
		for (PointFeature eq: bigEqs) {
			markers.add(new SimplePointMarker(
					eq.getLocation(),
					eq.getProperties()
					));
		}
		
//		Marker valMk = new SimplePointMarker(valLoc, valEq.getProperties());
		map.addMarkers(markers);
	}
	
	public void draw() {
		background(10);
		map.draw();
//		addKey();
	}
}
