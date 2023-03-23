package com.mindfire.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.constants.ResponseConstant;
import com.mindfire.request.IssueDto;
import com.mindfire.response.ApiResponse;
import com.mindfire.servicesinterface.IIssueService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("issues/")
public class IssueController {

	@Autowired
	private IIssueService service;

	@PostMapping
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Bearer dsfjbsdfjasjdfweuf_dsjfbdsfsd_342kbr2k", required = false, dataType = "string", paramType = "header"), })
	public ResponseEntity<ApiResponse<Boolean>> createNewIssue(@RequestBody IssueDto requestObj) {
		boolean isIssueCreated = service.createIssue(requestObj);
		ApiResponse<Boolean> apiResponse = new ApiResponse<>();
		apiResponse.setResult(isIssueCreated);
		apiResponse.setMessage(ResponseConstant.ISSUE_CREATE);
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Bearer dsfjbsdfjasjdfweuf_dsjfbdsfsd_342kbr2k", required = false, dataType = "string", paramType = "header"), })
	public ResponseEntity<ApiResponse<List<IssueDto>>> listAllIssuue() {
		List<IssueDto> listAllIssue = service.listAllIssue();
		ApiResponse<List<IssueDto>> apiResponse = new ApiResponse<>();
		apiResponse.setResult(listAllIssue);
		apiResponse.setMessage(ResponseConstant.ISSUE_LIST);
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/{id}")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Bearer dsfjbsdfjasjdfweuf_dsjfbdsfsd_342kbr2k", required = false, dataType = "string", paramType = "header"), })
	public ResponseEntity<ApiResponse<IssueDto>> listIssuueById(@PathVariable(value = "id") Integer issueId) {
		IssueDto issue = service.fetchIssueById(issueId);
		ApiResponse<IssueDto> apiResponse = new ApiResponse<>();
		apiResponse.setResult(issue);
		apiResponse.setMessage(ResponseConstant.ISSUE_LIST);
		return ResponseEntity.ok(apiResponse);
	}

	@PutMapping("/{id}")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Bearer dsfjbsdfjasjdfweuf_dsjfbdsfsd_342kbr2k", required = false, dataType = "string", paramType = "header"), })
	public ResponseEntity<ApiResponse<Boolean>> editIssuueById(@RequestBody IssueDto requestObj) {
		boolean editIssue = service.editIssue(requestObj);
		ApiResponse<Boolean> apiResponse = new ApiResponse<>();
		apiResponse.setResult(editIssue);
		apiResponse.setMessage(ResponseConstant.ISSUE_EDIT);
		return ResponseEntity.ok(apiResponse);
	}

	@DeleteMapping("/{id}")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Bearer dsfjbsdfjasjdfweuf_dsjfbdsfsd_342kbr2k", required = false, dataType = "string", paramType = "header"), })
	public ResponseEntity<ApiResponse<Boolean>> deleteIssuueById(@PathVariable(value = "id") Integer issueId) {
		boolean issue = service.issueDeleteById(issueId);
		ApiResponse<Boolean> apiResponse = new ApiResponse<>();
		apiResponse.setResult(issue);
		apiResponse.setMessage(ResponseConstant.ISSUE_DELETE);
		return ResponseEntity.ok(apiResponse);
	}
}
