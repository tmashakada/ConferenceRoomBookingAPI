# ConferenceRoomBookingAPI

# Technology Stack Used
# 1. Java 17
# 2 .Spring Boot 3
# 3. Maven
# 4. H2 in memory Database
 

git clone https://github.com/tmashakada/ConferenceRoomBookingAPI.git

1. Run the Spring Boot app with the java -jar command
   
   build your maven project using mvn clean install 
   
   1.mvn clean install
   

   2.Change the directory to the current project directory 
      and run the following command in cmd.
     
	 java -jar target/ConferenceRoomBookingAPI-0.0.1-SNAPSHOT.jar
	
	
	
	3.SwaggerUI URL:http://localhost:8082/swagger-ui/index.html
	   or You can use PostManClient
	   
	
	4.BookConferenceRoom
	
	    http://localhost:8082/api/bookings
		  BookingRequest payload
			{
			  "startDateTime": "2023-11-06 14:00",
			  "endDatetime": "2023-11-06 14:30",
			  "participants": 5
			}
	
	5.Get All ConferenceRooms
	    http://localhost:8082/api/rooms
	
	
	6.Get all Available Rooms
   
	    http://localhost:8082/api/rooms/available?startTime=2023-11-06 14:00&endTime=2023-11-06 14:30
	
	7.Get all maintenance-times
	
	    http://localhost:8082/api/maintenance-times
	
	
	8.InMemory Database URL
   
	   http://localhost:8082/h2-console
   
	   username:sa
   
	   password:password
   
	   JDBC URL: jdbc:h2:mem:bookingsdb
	
	
3. Run the ConferenceRoomBookingAPI with Maven Plugin
     
	 mvn spring-boot:run   when maven is installed on the machine
	 
	 ./mvnw spring-boot:run  when macen is not installed the machine
	 