package spring.mvc.model.dao;

import java.util.Optional;

import spring.mvc.model.entity.Ticket;

public interface TicketDao {

	
	//新增車票
	static void addTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		
	}
	
	//根據車票ID與userId查找車票資訊
	Optional<Ticket>findTicketByTicketIdAndUserId(Integer ticketId, String userId);
	
	//根據車票ID與userId修改車票資訊
	Boolean updateTicketByTicketIdAndUserId(Integer ticketId, String userId,Ticket newTicket);
	
	//根據車票ID與userId刪除車票
	Boolean cancelTicket(Integer ticketId,String userId);
	
	
}
