package com.mindfire.servicesinterface;

import java.util.List;

import com.mindfire.request.IssueDto;

public interface IIssueService {

	public boolean createIssue(IssueDto dto);

	public List<IssueDto> listAllIssue();

	public IssueDto fetchIssueById(int id);
	
	public boolean editIssue(IssueDto dto);
	
	public boolean issueDeleteById(int id);
}
