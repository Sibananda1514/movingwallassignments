package com.mindfire.servicesimpl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.entities.Issue;
import com.mindfire.exception.CustomException;
import com.mindfire.repository.IssueRepositry;
import com.mindfire.request.IssueDto;
import com.mindfire.servicesinterface.IIssueService;

@Service
public class IssueServiceImpl implements IIssueService {

	@Autowired
	private IssueRepositry issueRepo;

	/**
	 * Creates an Issue based on the provided IssueDto.
	 * 
	 * @param dto the IssueDto containing the details of the Issue to be created
	 * @return a boolean indicating whether the Issue was successfully created or
	 *         not
	 */
	@Override
	public boolean createIssue(IssueDto dto) {
		// Create a new Issue entity object and populate it with the details from the
		// provided
		// IssueDto object
		Issue ent = new Issue();
		BeanUtils.copyProperties(dto, ent);

		// Save the new Issue entity to the database
		Issue savedEntity = issueRepo.save(ent);

		// Return true if the Issue entity was successfully saved, false otherwise
		return !Objects.isNull(savedEntity);
	}

	/**
	 * 
	 * Retrieves a list of all Issues.
	 * 
	 * @return a List of IssueDto objects, each containing the details of an Issue
	 */
	@Override
	public List<IssueDto> listAllIssue() {
		// Retrieve a List of all Issue entities from the database, and convert each
		// entity to an IssueDto object
		List<IssueDto> allIssues = issueRepo.findAll().stream().map((ent) -> {
			IssueDto dto = new IssueDto();
			BeanUtils.copyProperties(ent, dto);
			return dto;
		}).collect(Collectors.toList());

		// Return the List of IssueDto objects
		return allIssues;
	}

	/**
	 * 
	 * Retrieves an Issue by ID.
	 * 
	 * @param id the ID of the Issue to retrieve
	 * @return an IssueDto object containing the details of the retrieved Issue
	 * @throws CustomException if no Issue is found with the given ID
	 */
	@Override
	public IssueDto fetchIssueById(int id) throws CustomException {
		IssueDto dto = new IssueDto();

		// Retrieve the Issue entity with the given ID, or throw a CustomException if no
		// entity is found
		Issue issue = issueRepo.findById(id).orElseThrow(() -> {
			throw new CustomException("No issue found with given id");
		});

		BeanUtils.copyProperties(issue, dto);

		// Return the IssueDto object
		return dto;
	}

	/**
	 * Updates an existing Issue with new details provided in the IssueDto object.
	 * 
	 * @param dto the IssueDto object containing the updated details of the Issue
	 * @return a boolean indicating whether the Issue was successfully updated or
	 *         not
	 * @throws CustomException if no Issue is found with the given ID
	 */
	@Override
	public boolean editIssue(IssueDto dto) throws CustomException {
		// Retrieve the existing Issue entity with the given ID, or throw a
		// CustomException if no entity is found
		Issue existingIssue = issueRepo.findById(dto.getId()).orElseThrow(() -> {
			throw new CustomException("No issue found with given id");
		});

		BeanUtils.copyProperties(dto, existingIssue);

		// Save the updated Issue entity to the database
		Issue savedEntity = issueRepo.save(existingIssue);

		// Return true if the Issue entity was successfully saved, false otherwise
		return !Objects.isNull(savedEntity);
	}

	/**
	 * 
	 * Deletes an Issue by ID.
	 * @param id the ID of the Issue to delete
	 * @return a boolean indicating whether the Issue was successfully deleted or
	 *         not
	 */
	@Override
	public boolean issueDeleteById(int id) {
		try {
			// Attempt to delete the Issue entity with the given ID from the database
			issueRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			// If an error occurs during deletion, catch the exception and return false
			return false;
		}
	}

}
