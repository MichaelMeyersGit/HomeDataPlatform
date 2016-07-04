package de.mchme.homedataplatform;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.mchme.homedataplatform.controler.TemperatureExportBody;
import de.mchme.homedataplatform.data.TemperatureData;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { HomeDataPlatformApplication.class , TestConfig.class } )
@WebAppConfiguration
@ActiveProfiles("example")
public class HomeDataPlatformApplicationTests {

	@Autowired 
	private MockMvc mvc;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testAddingTemperature() {
		
			
		try {
			
			List<TemperatureData> tempList = new ArrayList<TemperatureData>();
			
			Date now = Calendar.getInstance().getTime();
			
			TemperatureData d1 = new TemperatureData();
			d1.setIdentifier(1);
			d1.setTemperature(20.45);
			d1.setLogDate(now);
			d1.setUnit('K');
			
			TemperatureData d2 = new TemperatureData();
			d2.setIdentifier(1);
			d2.setTemperature(20.48);
			d2.setLogDate(now);
			d2.setUnit('C');
			
			tempList.add(d1);
			tempList.add(d2);
						
			GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm");
			Gson gson = gsonBuilder.create();
			
			String json = gson.toJson(tempList);
			
			ResultActions actions = mvc.perform(post("/temperature/add").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(json));
			
			MvcResult result = actions.andReturn();
			
			int status = result.getResponse().getStatus();
			
			Assert.assertTrue(status == HttpStatus.OK.value());
		
		} catch (Exception e) {
			Assert.fail();
		}
		
	}
	
	@Test
	public void testMissingFieldIdentifierAddingTemperature() {
		
			
		try {
			
			List<TemperatureData> tempList = new ArrayList<TemperatureData>();
			
			Date now = Calendar.getInstance().getTime();
			
			TemperatureData d1 = new TemperatureData();
			//d1.setIdentifier(1);
			d1.setTemperature(20.45);
			d1.setLogDate(now);
			d1.setUnit('F');
			
			
			tempList.add(d1);
						
			GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm");
			Gson gson = gsonBuilder.create();
			
			String json = gson.toJson(tempList);
			
			ResultActions actions = mvc.perform(post("/temperature/add").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(json));
			
			MvcResult result = actions.andReturn();
			
			int status = result.getResponse().getStatus();
			
			Assert.assertTrue(status == HttpStatus.BAD_REQUEST.value());
		
		} catch (Exception e) {
			Assert.fail();
		}
		
	}
	
	@Test
	public void testMissingFieldLogDateAddingTemperature() {
		
			
		try {
			
			List<TemperatureData> tempList = new ArrayList<TemperatureData>();
			
		//	Date now = Calendar.getInstance().getTime();
			
			TemperatureData d1 = new TemperatureData();
			d1.setIdentifier(1);
			d1.setTemperature(20.45);
		//	d1.setLogDate(now);
			d1.setUnit('C');
			
			
			tempList.add(d1);
						
			GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm");
			Gson gson = gsonBuilder.create();
			
			String json = gson.toJson(tempList);
			
			ResultActions actions = mvc.perform(post("/temperature/add").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(json));
			
			MvcResult result = actions.andReturn();
			
			int status = result.getResponse().getStatus();
			
			Assert.assertTrue(status == HttpStatus.BAD_REQUEST.value());
		
		} catch (Exception e) {
			Assert.fail();
		}
		
	}
	
	@Test
	public void testMissingFieldUnitAddingTemperature() {
		
			
		try {
			
			List<TemperatureData> tempList = new ArrayList<TemperatureData>();
			
			Date now = Calendar.getInstance().getTime();
			
			TemperatureData d1 = new TemperatureData();
			d1.setIdentifier(1);
			d1.setTemperature(20.45);
			d1.setLogDate(now);
		//	d1.setUnit(1);
			
			
			tempList.add(d1);
						
			GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm");
			Gson gson = gsonBuilder.create();
			
			String json = gson.toJson(tempList);
			
			ResultActions actions = mvc.perform(post("/temperature/add").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(json));
			
			MvcResult result = actions.andReturn();
			
			int status = result.getResponse().getStatus();
			
			Assert.assertTrue(status == HttpStatus.BAD_REQUEST.value());
		
		} catch (Exception e) {
			Assert.fail();
		}
		
	}
	
	@Test
	public void testMissingFieldTemperatureAddingTemperature() {
		
			
		try {
			
			List<TemperatureData> tempList = new ArrayList<TemperatureData>();
			
			Date now = Calendar.getInstance().getTime();
			
			TemperatureData d1 = new TemperatureData();
			d1.setIdentifier(1);
		//	d1.setTemperature(20.45);
			d1.setLogDate(now);
			d1.setUnit('K');
			
			
			tempList.add(d1);
						
			GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm");
			Gson gson = gsonBuilder.create();
			
			String json = gson.toJson(tempList);
			
			ResultActions actions = mvc.perform(post("/temperature/add").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(json));
			
			MvcResult result = actions.andReturn();
			
			int status = result.getResponse().getStatus();
			
			Assert.assertTrue(status == HttpStatus.BAD_REQUEST.value());
		
		} catch (Exception e) {
			Assert.fail();
		}
		
	}

	
	@Test
	public void testTemperatureExport() {
		
			
		try {
			
					
			Date now = Calendar.getInstance().getTime();
			
			TemperatureExportBody export = new TemperatureExportBody();
			
			export.setIdentifier(1);
			export.setEnddate(now);
			export.setStartdate(now);
			
			GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm");
			Gson gson = gsonBuilder.create();
			
			String json = gson.toJson(export);
				
			
			ResultActions actions = mvc.perform(post("/temperature/export").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(json));
			
			MvcResult result = actions.andReturn();
			
			int status = result.getResponse().getStatus();
			
			Assert.assertTrue(status == HttpStatus.OK.value());
		
		} catch (Exception e) {
			Assert.fail();
		}
		
	}
	
	@Test
	public void testTemperatureExportNoDataFound() {
		
			
		try {
			
					
			Date enddate = Calendar.getInstance().getTime();
			Calendar cal = Calendar.getInstance() ;
			cal.add(Calendar.DATE, 1);
			
			Date startdate = cal.getTime();
						
			TemperatureExportBody export = new TemperatureExportBody();
			
			export.setIdentifier(1);
			export.setEnddate(enddate);
			export.setStartdate(startdate);
			
			GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm");
			Gson gson = gsonBuilder.create();
			
			String json = gson.toJson(export);
				
			
			ResultActions actions = mvc.perform(post("/temperature/export").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(json));
			
			MvcResult result = actions.andReturn();
			
			int status = result.getResponse().getStatus();
			
			Assert.assertTrue(status == HttpStatus.NO_CONTENT.value());
		
		} catch (Exception e) {
			Assert.fail();
		}
		
	}
}
