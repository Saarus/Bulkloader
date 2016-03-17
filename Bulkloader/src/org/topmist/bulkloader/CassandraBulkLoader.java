package org.topmist.bulkloader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.supercsv.io.CsvListReader;
import org.supercsv.prefs.CsvPreference;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.QueryOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.DefaultRetryPolicy;

//This sample code reads flights data from flights_from_pg.csv in project's base director) and bulk loads in Cassandra 
// using Cassandra's Java driver

/*	 Create a single or multi node DSE cassandra cluster using scripts shared here:
	 http://www.datastax.com/dev/blog/running-multiple-datastax-enterprise-nodes-in-a-single-host
	 Next create a "demo" keyspace using cql command line
	 Then create a " flights " table using this command:
		 CREATE TABLE demo.flights (
			ID int PRIMARY KEY, 
			YEAR int, 
			DAY_OF_MONTH int, 
			FL_DATE timestamp, 
			AIRLINE_ID int, 
			CARRIER varchar, 
			FL_NUM int, 
			ORIGIN_AIRPORT_ID int, 
			ORIGIN varchar, 
			ORIGIN_CITY_NAME varchar, 
			ORIGIN_STATE_ABR varchar, 
			DEST varchar, 
			DEST_CITY_NAME varchar, 
			DEST_STATE_ABR varchar, 
			DEP_TIME timestamp, 
			ARR_TIME timestamp, 
			ACTUAL_ELAPSED_TIME timestamp, 
			AIR_TIME timestamp, 
			DISTANCE int);
*/			


public class CassandraBulkLoader {

	public static void main(String[] args) {

		CsvListReader csvReader = null;
		Cluster cluster;
		Session session;
		
		
		
		// Connect to the cluster and keyspace "demo"
		cluster = Cluster
				.builder()
				.addContactPoint("192.xxx.xxx.xxx") // Host IP of Cassandra node
				.withRetryPolicy(DefaultRetryPolicy.INSTANCE)
				.withQueryOptions(
						new QueryOptions()
								.setConsistencyLevel(ConsistencyLevel.ANY))
				.build();
		session = cluster.connect("demo");
		int count = 0;
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(
									new File(
											"flights_from_pg.csv"))));
			csvReader = new CsvListReader(reader,
					CsvPreference.STANDARD_PREFERENCE);

			BatchStatement batch = new BatchStatement();
			PreparedStatement ps = session
					.prepare("INSERT INTO demo.flights "
							+ "(ID,YEAR,DAY_OF_MONTH,FL_DATE,AIRLINE_ID,CARRIER,FL_NUM,"
							+ "ORIGIN_AIRPORT_ID,ORIGIN,ORIGIN_CITY_NAME,ORIGIN_STATE_ABR,"
							+ "DEST,DEST_CITY_NAME,DEST_STATE_ABR,DEP_TIME,ARR_TIME,ACTUAL_ELAPSED_TIME,"
							+ "AIR_TIME,DISTANCE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			List<String> data;
			while ((data = csvReader.read()) != null) {
				count++;
				if (count < 10000) {
					DateFormat dtFormat = new SimpleDateFormat("yyyy/MM/dd");
					Date flDate = dtFormat.parse(data.get(3));
					System.out.println("Index: " + data.get(0));

					SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm"); // 12
																					// hour
																					// format
					Date depTime = timeformat
							.parse(fixTimeformat(data.get(14)));
					Date arrTime = timeformat
							.parse(fixTimeformat(data.get(15)));
					Date elapsedTime = timeformat.parse(fixTimeformat(data
							.get(16)));
					batch.add(ps.bind(Integer.parseInt(data.get(0)),
							Integer.parseInt(data.get(1)),
							Integer.parseInt(data.get(2)), flDate,
							Integer.parseInt(data.get(4)), data.get(5),
							Integer.parseInt(data.get(6)),
							Integer.parseInt(data.get(7)), data.get(8),
							data.get(9), data.get(10), data.get(11),
							data.get(12), data.get(13), depTime, arrTime,
							elapsedTime,
							timeformat.parse(fixTimeformat(data.get(17))),
							Integer.parseInt(data.get(18))));
				} else {
					// now execute the batch
					session.execute(batch);
					Thread.sleep(180000);
					count = 0;
				}

			}

		} catch (Exception exp) {
			exp.printStackTrace();

		} finally {
			if (csvReader != null) {
				try {
					csvReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	private static String fixTimeformat(String data) {
		System.out.println(data);
		if (data.length() == 2) {
			data = "00" + data;
		}
		if (data.length() == 1) {
			data = data + "000";
		}

		data = new StringBuilder(data).insert(data.length() - 2, ":")
				.toString();
		return data;
	}

}