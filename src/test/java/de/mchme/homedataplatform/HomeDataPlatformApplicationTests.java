package de.mchme.homedataplatform;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
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

import de.mchme.homedataplatform.data.SimpleSensorStateData;
import de.mchme.homedataplatform.data.TemperatureData;
import de.mchme.homedataplatform.repositories.TemperatureRepository;
import de.mchme.homedataplatform.simplesensor.controller.SimpleSensorStateRestController;
import de.mchme.homedataplatform.temperature.controller.TemperatureRestControler;
import de.mchme.homedataplatform.utils.RulesUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { HomeDataPlatformApplication.class , TestConfig.class } )
@WebAppConfiguration
@ActiveProfiles("unit")
public class HomeDataPlatformApplicationTests {
	
//	private final static Logger logger = LoggerFactory.getLogger(HomeDataPlatformApplicationTests.class);

	private final int TIMESPAN = 60 ;
	private final double THRESHHOLD = 20.0 ;
	
	@Autowired 
	private MockMvc mvc;
	
	@Autowired
	private TemperatureRepository tempRepo ;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testAddingTemperatureList() {
		
			
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
			
			ResultActions actions = mvc.perform(post(TemperatureRestControler.ADDLIST).contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(json));
			
			MvcResult result = actions.andReturn();
			
			int status = result.getResponse().getStatus();
			
			Assert.assertTrue(status == HttpStatus.OK.value());
		
		} catch (Exception e) {
			Assert.fail();
		}
		
	}
	
	@Test
	public void testAddSingleTemperature() {
		
			
		try {
			
			
			Date now = Calendar.getInstance().getTime();
			
			TemperatureData d1 = new TemperatureData();
			d1.setIdentifier(1);
			d1.setTemperature(20.45);
			d1.setLogDate(now);
			d1.setUnit('K');
			
			
			GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm");
			Gson gson = gsonBuilder.create();
			
			String json = gson.toJson(d1);
			
			ResultActions actions = mvc.perform(post(TemperatureRestControler.ADDSINGLE).contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(json));
			
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
			
			ResultActions actions = mvc.perform(post(TemperatureRestControler.ADDLIST).contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(json));
			
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
			
			ResultActions actions = mvc.perform(post(TemperatureRestControler.ADDLIST).contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(json));
			
			MvcResult result = actions.andReturn();
			
			int status = result.getResponse().getStatus();
			
			Assert.assertTrue(status == HttpStatus.OK.value());
		
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
			
			ResultActions actions = mvc.perform(post(TemperatureRestControler.ADDLIST).contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(json));
			
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
			
			ResultActions actions = mvc.perform(post(TemperatureRestControler.ADDLIST).contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(json));
			
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
			
			this.clearDatabase();
			this.fillDatabase();
					
			ResultActions actions = mvc.perform(post("/temperature/excelexport").param("identifier", "1").param("startdate", "2016-02-01 01:00").param("enddate", "2016-02-01 23:00"));
		
			
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
							
			ResultActions actions = mvc.perform(post("/temperature/excelexport").param("identifier", "1").param("startdate", "2016-07-12 12:00").param("enddate", "2016-07-11 12:00"));
		
			
			MvcResult result = actions.andReturn();
			
			int status = result.getResponse().getStatus();
			
			Assert.assertTrue(status == HttpStatus.NO_CONTENT.value());
		
		} catch (Exception e) {
			Assert.fail();
		}
		
	}
	

	@Test
	public void testTemperatureSearch() {
		
		try {
			this.clearDatabase();
			this.fillDatabase();
			
			Calendar cal = Calendar.getInstance();
			cal.set(2016, Calendar.FEBRUARY, 1, 1, 0);
			
			Date startdate = cal.getTime();
			
			cal.set(2016, Calendar.FEBRUARY, 1, 11, 0);
			Date enddate = cal.getTime();
			
			List<TemperatureData> templist = this.tempRepo.findByIdentifierAndLogDateBetween(1, startdate, enddate);
			
			int size = templist.size();
			
			Assert.assertTrue(size == 2);
			
		} catch (IOException | ParseException e) {
			Assert.fail();
		}
		
	}
	
	@Test
	public void testTemperatureSearchWithoutDate() {
		
		try {
			this.clearDatabase();
					
			List<TemperatureData> tempList = new ArrayList<TemperatureData>();
			
			TemperatureData d1 = new TemperatureData();
			d1.setIdentifier(1);
		    d1.setTemperature(20.45);
			d1.setUnit('F');
			
			tempList.add(d1);
			
			GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm");
			Gson gson = gsonBuilder.create();
			
			String json = gson.toJson(tempList);
			
		    mvc.perform(post(TemperatureRestControler.ADDLIST).contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(json));
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 1);
			
			Date startdate = cal.getTime();
			
			cal.set(Calendar.HOUR_OF_DAY , 23);
			
			Date enddate = cal.getTime();
			
			List<TemperatureData> templist = this.tempRepo.findByIdentifierAndLogDateBetween(1, startdate, enddate);
			
			int size = templist.size();
			
			Assert.assertTrue(size == 1);
			
		} catch ( Exception e) {
			Assert.fail();
		}
		
	}
	
	@Test
	public void testTemperatureSearchWithoutDateFail() {
		
		try {
			this.clearDatabase();
					
			List<TemperatureData> tempList = new ArrayList<TemperatureData>();
			
			TemperatureData d1 = new TemperatureData();
			d1.setIdentifier(1);
		    d1.setTemperature(20.45);
			d1.setUnit('F');
			
			tempList.add(d1);
			
			GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm");
			Gson gson = gsonBuilder.create();
			
			String json = gson.toJson(tempList);
			
		    mvc.perform(post(TemperatureRestControler.ADDLIST).contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(json));
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 1);
			cal.set(Calendar.YEAR, 2015);
			
			Date startdate = cal.getTime();
			
			cal.set(Calendar.HOUR_OF_DAY , 23);
			
			Date enddate = cal.getTime();
			
			List<TemperatureData> templist = this.tempRepo.findByIdentifierAndLogDateBetween(1, startdate, enddate);
			
			int size = templist.size();
			
			Assert.assertTrue(size == 0);
			
		} catch ( Exception e) {
			Assert.fail();
		}
		
	}
	
	@Test
	public void testFindByLogDateGreatherThenAndByTemperatureGreaterThenSucces() {
		final double t = THRESHHOLD;
		
		this.clearDatabase();
		
		TemperatureData dt = new TemperatureData();
		dt.setIdentifier(1);
		dt.setLogDate(Calendar.getInstance().getTime());
		dt.setTemperature(t);
		dt.setUnit('C');
		
		this.tempRepo.save(dt);
		
		Date startdate = RulesUtils.getTimespanDate(TIMESPAN);
		
		List<TemperatureData> list = this.tempRepo.findByLogDateGreaterThanAndTemperatureGreaterThanEqual(startdate, THRESHHOLD);
		
		int size = list.size() ;
		
		Assert.assertTrue(size == 1);
	}
	
	@Test
	public void testFindByLogDateGreatherThenAndByTemperatureGreaterThenFailDate() {
		final double t = 21.0 ;
		
		this.clearDatabase();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2015);
		
		Date logdate = cal.getTime();
		
		TemperatureData dt = new TemperatureData();
		dt.setIdentifier(1);
		dt.setLogDate(logdate);
		dt.setTemperature(t);
		dt.setUnit('C');
		
		this.tempRepo.save(dt);
		
		Date startdate = RulesUtils.getTimespanDate(TIMESPAN);
		
		List<TemperatureData> list = this.tempRepo.findByLogDateGreaterThanAndTemperatureGreaterThanEqual(startdate, THRESHHOLD);
		
		int size = list.size() ;
		
		Assert.assertTrue(size == 0);
	}
	
	@Test
	public void testFindByLogDateGreatherThenAndByTemperatureGreaterThenFailTemperature() {
		final double t = THRESHHOLD - 0.1 ;
		
		this.clearDatabase();
		
		TemperatureData dt = new TemperatureData();
		dt.setIdentifier(1);
		dt.setLogDate(Calendar.getInstance().getTime());
		dt.setTemperature(t);
		dt.setUnit('C');
		
		this.tempRepo.save(dt);
		
		Date startdate = RulesUtils.getTimespanDate(TIMESPAN);
		
		List<TemperatureData> list = this.tempRepo.findByLogDateGreaterThanAndTemperatureGreaterThanEqual(startdate, THRESHHOLD);
		
		int size = list.size() ;
		
		Assert.assertTrue(size == 0);
		
	}
	
	@Test
	public void testAddSingleSensorState() {
		
			
		try {
			SimpleSensorStateData dt = new SimpleSensorStateData();
			dt.setIdentifier(1);
			dt.setSensorType(3);
			dt.setState(0);
			
			
			GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm");
			Gson gson = gsonBuilder.create();
			
			String json = gson.toJson(dt);
			
			ResultActions actions = mvc.perform(post(SimpleSensorStateRestController.ADD).contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(json));
			
			MvcResult result = actions.andReturn();
			
			int status = result.getResponse().getStatus();
			
			Assert.assertTrue(status == HttpStatus.OK.value());
		
		} catch (Exception e) {
			Assert.fail();
		}
		
	}
	
	@Test
	public void testAddSingleSensorStateFail() {
		
			
		try {
			SimpleSensorStateData dt = new SimpleSensorStateData();
			dt.setSensorType(3);
			dt.setState(0);
			
			
			GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm");
			Gson gson = gsonBuilder.create();
			
			String json = gson.toJson(dt);
			
			ResultActions actions = mvc.perform(post(SimpleSensorStateRestController.ADD).contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(json));
			
			MvcResult result = actions.andReturn();
			
			int status = result.getResponse().getStatus();
			
			Assert.assertTrue(status == HttpStatus.BAD_REQUEST.value());
		
		} catch (Exception e) {
			Assert.fail();
		}
		
	}
	
	
	
	
	
	private void clearDatabase() {
		this.tempRepo.deleteAll();
	}
	
	private void fillDatabase() throws IOException, ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		
		NumberFormat numberFormat = NumberFormat.getInstance(Locale.FRANCE);
		
		Resource resource = this.resourceLoader.getResource("classpath:Temperaturedata.csv");
		File f = resource.getFile();
		
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.newFormat(';').parse(in);
		
		
		
		for (CSVRecord record : records) {
		    String columnOne = record.get(0);
		    String columnTwo = record.get(1);
		    String unit = record.get(2);
		    String columnFour = record.get(3);
		    
		    Integer identifier = Integer.parseInt(columnOne);
		    Number number = numberFormat.parse(columnFour);
		    Double temperature = number.doubleValue();
		    Date logdate = dateFormat.parse(columnTwo);
		    
		    TemperatureData dt = new TemperatureData();
		    dt.setIdentifier(identifier);
		    dt.setLogDate(logdate);
		    dt.setTemperature(temperature);
		    dt.setUnit(unit.charAt(0));
		    
		    this.tempRepo.save(dt);
		    
		    
		}

	}
	
	
}
