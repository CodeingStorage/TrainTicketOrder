package spring.mvc.controller;

import java.sql.Time;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.mvc.model.entity.Ticket;
import spring.mvc.bean.TrainTime;
import spring.mvc.model.dao.ScheduleDao;
import spring.mvc.model.dao.TicketDao;
import spring.mvc.model.entity.Schedule;

@Controller
@RequestMapping("/ticket")
public class TicketController {
		

	@Autowired
	private  TicketDao ticketDao;

	Logger logger = LoggerFactory.getLogger(getClass());
		
	//前往首頁
	@GetMapping("/frontend/main")
	public String main(HttpSession session) {
		
		return "/frontend/main";

	}
//車票 訂/修/刪除(需要API)----------------------------------------------------------------------------------------------	
	
	//訂票頁面
	@GetMapping("/booking/booking")
	public String bookPage(HttpSession session, Model model) {
		return "/booking/booking";
		
	}
	//訂票
	@PostMapping("/booking/booking")
	public String booking(@RequestParam("departStation" )String departStation,
					   @RequestParam("arriveStation" )String arriveStation,					   
					   @RequestParam("departDate" )String departDate, Model model) throws Exception {

		if (departStation.equals(arriveStation)) {
			model.addAttribute("bookingMessage", "起點終點重複");
			return "booking";
		}
		//需要API
		//List<TrainTime> trainTimes = TimeTableAPI.getTrainTimes(departStation, arriveStation, departDate);
		
		//model.addAttribute("trainTimes", trainTimes);		
		
		return "booking"; 	
		
	}
	
	// 訂票結果(需要API)
		@PostMapping("/booking/choosing/result")
		@ResponseBody
		@Transactional(propagation = Propagation.REQUIRED)
		public String result(@RequestBody TrainTime trainTime,
							Model model, HttpSession session) {
						
			//從API獲取資訊(need API)
	        String trainNo = trainTime.getTrainNo();
	        String departStationName = trainTime.getDepartStationName();
	        String arriveStationName = trainTime.getArriveStationName();	        
	        Time departTime = trainTime.getDepartTime();
	        Time arriveTime = trainTime.getArriveTime();
	        String price = trainTime.getPrice();
	        
	        System.out.println(trainNo);
			
	        //schedule
	     	Schedule schedule = new Schedule();
	     	schedule.getTrainNo();
	     	schedule.setDepartStation(departStationName);
	     	schedule.setArriveStation(arriveStationName);	     	
	     	schedule.setDepartTime(departTime);
	     	schedule.setArriveTime(arriveTime);
	     	
			ScheduleDao.addSchedule(schedule);
			
			
			Ticket ticket = new Ticket();
			
			ticket.setUserId(ticket.getUserId());
			
			ticket.setTrainNo(schedule.getTrainNo());
			
			System.out.println(schedule.getTrainNo());
			
			
			//隨機選擇車廂
			Random r = new Random();
			char c = (char)(r.nextInt(5) + 'a');
			String s = Character.toString(c);
			ticket.setTrainCarId(s);
			
			//隨機選擇座位
			int i;
			i = (int) (Math.random()*10)+1;
			Integer iInteger = Integer.valueOf(i);
			ticket.setSeatId(iInteger);
			
			
			ticket.setPrice(Integer.parseInt(price));
			
			ticketDao.addTicket(ticket);
			
			logger.info("訂票成功");
			
			return "success:" + schedule.getTrainNo();
		}
		
		
		// 取消訂票
		@GetMapping("/ticketlist/cancel")
		public String cancelticket(@RequestParam("ticketId") Integer ticketId, @RequestParam("userId") String userId, HttpSession session) {
			ticketDao.cancelTicket(ticketId, userId);
			logger.info("取消訂票");
			return "redirect:/mvc/highrail/ticketlist";
		}
		
		

}
