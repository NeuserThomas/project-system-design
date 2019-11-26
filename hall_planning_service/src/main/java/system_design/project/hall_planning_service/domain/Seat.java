package system_design.project.hall_planning_service.domain;

public enum Seat {
	/**
	 * empty space, like a stair or hall in between rows
	 */
	hall,
	/**
	 * Normal seat
	 */
	normal_seat,
	/**
	 * cozy seat
	 */
	cozy_seat,
	/**
	 * VIP seat
	 */
	vip_seat
}
