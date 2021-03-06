package com.techelevator.clebrews.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCBreweryDAO implements BreweryDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCBreweryDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}	
	

	@Override
	public List<Brewery> getAllBrewery() {
		List<Brewery> allBreweries = new ArrayList<>();
		String sqlSelectAllBreweries = "SELECT * FROM breweries ORDER BY name";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllBreweries);
		while(results.next()) {
			allBreweries.add(mapRowToBrewery(results));
		}
		return allBreweries;
	}

	@Override
	public void saveBrewery(String name, String address, String city, String zipcode, String phoneNumber, String description, String breweryLogoUrl, String imgUrl, String websiteUrl, String businessHours, String googleMapsUrl, String lat, String lng) {
		jdbcTemplate.update("INSERT INTO breweries(name, address, city, zipcode, phone_number, description, brewery_logo_url, img_url, website_url, business_hours, google_maps_url, lat, lng) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				name, address, city, zipcode, phoneNumber, description, breweryLogoUrl, imgUrl, websiteUrl, businessHours, googleMapsUrl, lat, lng);
	}


	@Override
	public boolean searchForBrewery(String name) {
		String sqlSearchForBrewery = "SELECT * " +
									 "FROM breweries " +
									 "WHERE UPPER(name) = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForBrewery, name.toUpperCase());
		if(results.next()) {
			return true;
		} else {
		return false;
		}
	}


	@Override
	public Brewery getBreweryById(long id) {
		Brewery details = new Brewery();
		String sqlGetBreweryById = "SELECT * FROM breweries WHERE brewery_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetBreweryById, id);
		
		if (results.next()) {
			details = mapRowToBrewery(results);
		}
		return details;
	}
	
	@Override
	public Brewery getBreweryByName(String name) {
		Brewery details = new Brewery();
		String sqlGetBreweryByName = "SELECT * FROM breweries WHERE name = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetBreweryByName, name);
		
		if (results.next()) {
			details = mapRowToBrewery(results);
		}
		return details;
	}
	


	@Override
	public Brewery getBreweryByUserId(long userId) {
		
		Brewery breweries = new Brewery();
		String sqlSelectBrewerieByUserId = "SELECT * FROM breweries WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectBrewerieByUserId, userId);
		
		while(results.next()) {
			breweries= mapRowToBrewery(results);
		}
		return breweries;
	}
	
	
	public List<Brewery> getBreweryWhereUserIdIsNULL() {
		List<Brewery> breweryList = new ArrayList<>();
		String sqlGetBreweryById = "SELECT * FROM breweries WHERE user_id IS NULL";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetBreweryById);
		while (results.next()) {
			breweryList.add(mapRowToBrewery(results));
		}
		return breweryList;
	}
	
	private Brewery mapRowToBrewery(SqlRowSet row) {
		Brewery newBrewery = new Brewery();
		newBrewery.setId(row.getInt("brewery_id"));
		newBrewery.setName(row.getString("name"));
		newBrewery.setAddress(row.getString("address"));
		newBrewery.setCity(row.getString("city"));
		newBrewery.setZipcode(row.getString("zipcode"));
		newBrewery.setPhoneNumber(row.getString("phone_number"));
		newBrewery.setDescription(row.getString("description"));
		newBrewery.setBreweryLogoUrl(row.getString("brewery_logo_url"));
		newBrewery.setImgUrl(row.getString("img_url"));
		newBrewery.setWebsiteUrl(row.getString("website_url"));
		newBrewery.setBusinessHours(row.getString("business_hours"));
		newBrewery.setLat(row.getString("lat"));
		newBrewery.setLng(row.getString("lng"));
		newBrewery.setGoogleMapsUrl(row.getString("google_maps_url"));
		newBrewery.setUserId(row.getLong("user_id"));
		return newBrewery;
	}


	@Override
	public void updateBrewery(String name, String address, String city, String zipcode, String phoneNumber,
			String description, String breweryLogoUrl, String imgUrl, String websiteUrl, String businessHours, String googleMapsUrl, String lat, String lng, long id) {
		jdbcTemplate.update("UPDATE breweries SET name = ?, address =? , city = ?, zipcode = ?, phone_number = ?, description = ?, "
				+ "brewery_logo_url = ?, img_url = ?, website_url = ?, business_hours = ?, google_maps_url = ?, lat = ?, lng = ? WHERE brewery_id = ?",
				name, address, city, zipcode, phoneNumber, description, breweryLogoUrl, imgUrl, websiteUrl, businessHours, googleMapsUrl, lat, lng, id);		
	}


	@Override
	public boolean updateBreweryUserId(long id, int userId) {
		String sqlUpdateBreweryUserId = "UPDATE breweries SET user_id = ? WHERE brewery_id = ?";
		jdbcTemplate.update(sqlUpdateBreweryUserId, userId, id);
		return true;
	}

}
