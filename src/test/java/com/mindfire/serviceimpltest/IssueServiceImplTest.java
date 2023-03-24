package com.mindfire.serviceimpltest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mindfire.entities.Issue;
import com.mindfire.exception.CustomException;
import com.mindfire.repository.IssueRepositry;
import com.mindfire.request.IssueDto;
import com.mindfire.servicesimpl.IssueServiceImpl;

public class IssueServiceImplTest {

	@Mock
	private IssueRepositry issueRepo;

	@InjectMocks
	private IssueServiceImpl issueServiceImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void createIssueTest() {
		IssueDto issueDto = new IssueDto();
		issueDto.setId(1);
		issueDto.setDescription("Issue Details");
		issueDto.setSeverity("High");

		Issue issue = new Issue();
		issue.setId(1);
		issue.setDescription("Issue Details");
		issue.setSeverity("High");

		when(issueRepo.save(any(Issue.class))).thenReturn(issue);

		boolean result = issueServiceImpl.createIssue(issueDto);

		assertEquals(true, result);
	}

	@Test
	void listAllIssueTest() {
		Issue issue1 = new Issue();
		issue1.setId(1);
		issue1.setDescription("Issue Details 1");
		issue1.setSeverity("High");

		Issue issue2 = new Issue();
		issue2.setId(2);
		issue2.setDescription("Issue Details 2");
		issue2.setSeverity("Low");

		List<Issue> issueList = new ArrayList<>();
		issueList.add(issue1);
		issueList.add(issue2);

		when(issueRepo.findAll()).thenReturn(issueList);

		List<IssueDto> result = issueServiceImpl.listAllIssue();

		assertEquals(2, result.size());
		assertEquals("Issue Details 1", result.get(0).getDescription());
		assertEquals("High", result.get(0).getSeverity());
		assertEquals("Issue Details 2", result.get(1).getDescription());
		assertEquals("Low", result.get(1).getSeverity());
	}

	@Test
	void fetchIssueByIdTest() throws CustomException {
		Issue issue = new Issue();
		issue.setId(1);
		issue.setDescription("Issue Details");
		issue.setSeverity("High");

		when(issueRepo.findById(1)).thenReturn(Optional.of(issue));

		IssueDto result = issueServiceImpl.fetchIssueById(1);

		assertEquals("Issue Details", result.getDescription());
		assertEquals("High", result.getSeverity());
	}

	@Test
	void fetchIssueByIdTestWithInvalidId() {
		when(issueRepo.findById(1)).thenReturn(Optional.empty());

		try {
			issueServiceImpl.fetchIssueById(1);
		} catch (CustomException ex) {
			assertEquals("No issue found with given id", ex.getMessage());
		}
	}
}
