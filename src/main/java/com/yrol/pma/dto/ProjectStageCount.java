package com.yrol.pma.dto;

public interface ProjectStageCount {

	/**
	 * IMPORTANT: Need to have the property names begin with "get"
	 * This DTO is being used by ProjectRepository DAO in some controllers
	 * */
	public String getStage();
	public Integer getProjectStageCount();

}
