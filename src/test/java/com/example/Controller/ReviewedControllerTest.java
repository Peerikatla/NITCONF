/*
 * package com.example.Controller;
 * 
 * import com.example.Service.Reviewedservice; import com.example.model.Paper;
 * import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test;
 * import org.mockito.InjectMocks; import org.mockito.Mock; import
 * org.mockito.MockitoAnnotations; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
 * import org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.http.MediaType; import
 * org.springframework.test.web.servlet.MockMvc; import
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
 * 
 * import java.util.Collections; import java.util.List; import java.util.Map;
 * 
 * import static org.mockito.Mockito.verify; import static
 * org.mockito.Mockito.when; import static org.mockito.Mockito.times; import
 * static org.mockito.Mockito.never; import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 * 
 * @SpringBootTest
 * 
 * @AutoConfigureMockMvc public class ReviewedControllerTest {
 * 
 * @Autowired private MockMvc mockMvc;
 * 
 * @Mock private Reviewedservice reviewedservice;
 * 
 * @InjectMocks private ReviewedController reviewedController;
 * 
 * @SuppressWarnings("deprecation")
 * 
 * @BeforeEach public void setup() { MockitoAnnotations.initMocks(this); }
 * 
 * @SuppressWarnings("null")
 * 
 * @Test public void testGetAllPapersWithSubmissions() throws Exception { //
 * Mock data Paper paper = new Paper(); // Mock service behavior
 * when(reviewedservice.getAllPapersWithSubmissions()).thenReturn(Collections.
 * singletonList(paper));
 * 
 * // Perform GET request to API endpoint
 * mockMvc.perform(MockMvcRequestBuilders.get("/api/submittedpapers"))
 * .andExpect(status().isOk())
 * .andExpect(content().contentType(MediaType.APPLICATION_JSON))
 * .andExpect(jsonPath("$[0].id").value(paper.getPaperId()))
 * .andExpect(jsonPath("$[0].title").value(paper.getTitle()));
 * 
 * // Verify service method is called verify(reviewedservice,
 * times(1)).getAllPapersWithSubmissions(); }
 * 
 * @SuppressWarnings("null")
 * 
 * @Test public void testGetPapersWithReviews() throws Exception { // Mock data
 * int userId = 1; List<Map<String, Object>> papersWithReviews =
 * Collections.singletonList(Collections.emptyMap()); // Mock service behavior
 * when(reviewedservice.getPapersWithReviews(userId)).thenReturn(
 * papersWithReviews);
 * 
 * // Perform GET request to API endpoint
 * mockMvc.perform(MockMvcRequestBuilders.get("/api/papers/{userId}/reviews",
 * userId)) .andExpect(status().isOk())
 * .andExpect(content().contentType(MediaType.APPLICATION_JSON))
 * .andExpect(jsonPath("$[0]").value(Collections.emptyMap()));
 * 
 * // Verify service method is called verify(reviewedservice,
 * times(1)).getPapersWithReviews(userId); }
 * 
 * @Test public void testUpdateCommentAndRating() throws Exception { // Mock
 * data int paperId = 1; int submissionId = 1; String comment =
 * "Updated comment"; int originality = 4; int relevance = 5; int quality = 3;
 * int technicalContentandAccuracy = 4; int significanceOfWork = 5; int
 * appropriateForSAC = 3;
 * 
 * 
 * // Perform PATCH request to API endpoint
 * mockMvc.perform(MockMvcRequestBuilders.patch(
 * "/api/reviewed/papers/{paperId}/submissions/{submissionId}/comment", paperId,
 * submissionId) .param("comment", comment) .param( "originality",
 * String.valueOf(originality)) .param("relevance", String.valueOf(relevance))
 * .param("quality", String.valueOf(quality))
 * .param("technicalContentandAccuracy",
 * String.valueOf(technicalContentandAccuracy)) .param("significanceOfWork",
 * String.valueOf(significanceOfWork)) .param("appropriateForSAC",
 * String.valueOf(appropriateForSAC))) .andExpect(status().isOk());
 * 
 * // Verify service method is called with correct arguments
 * verify(reviewedservice, times(1)).updatecomment(paperId, submissionId,
 * comment, originality, relevance, quality, technicalContentandAccuracy,
 * significanceOfWork, appropriateForSAC); }
 * 
 * @Test public void testUpdateCommentAndRatingWithInvalidRating() throws
 * Exception { // Mock data int paperId = 1; int submissionId = 1; String
 * comment = "Updated comment"; int originality = 4; int relevance = 5; int
 * quality = 6; // Invalid rating int technicalContentandAccuracy = 4; int
 * significanceOfWork = 5; int appropriateForSAC = 3; // Perform PATCH request
 * to API endpoint with invalid rating
 * mockMvc.perform(MockMvcRequestBuilders.patch(
 * "/api/reviewed/papers/{paperId}/submissions/{submissionId}/comment", paperId,
 * submissionId) .param("comment", comment) .param( "originality",
 * String.valueOf(originality)) .param("relevance", String.valueOf(relevance))
 * .param("quality", String.valueOf(quality))
 * .param("technicalContentandAccuracy",
 * String.valueOf(technicalContentandAccuracy)) .param("significanceOfWork",
 * String.valueOf(significanceOfWork)) .param("appropriateForSAC",
 * String.valueOf(appropriateForSAC))) .andExpect(status().isBadRequest());
 * 
 * // Verify service method is not called verify(reviewedservice,
 * never()).updatecomment(paperId, submissionId, comment, originality,
 * relevance, quality, technicalContentandAccuracy, significanceOfWork,
 * appropriateForSAC); }
 * 
 * @Test public void testUpdateCommentAndRatingWithEmptyComment() throws
 * Exception { // Mock data int paperId = 1; int submissionId = 2; String
 * comment = ""; // Empty comment int originality = 4; int relevance = 5; int
 * quality = 3; int technicalContentandAccuracy = 4; int significanceOfWork = 5;
 * int appropriateForSAC = 3;
 * 
 * // Perform PATCH request to API endpoint with empty comment
 * mockMvc.perform(MockMvcRequestBuilders.patch(
 * "/api/reviewed/papers/{paperId}/submissions/{submissionId}/comment", paperId,
 * submissionId) .param("comment", comment) .param( "originality",
 * String.valueOf(originality)) .param("relevance", String.valueOf(relevance))
 * .param("quality", String.valueOf(quality))
 * .param("technicalContentandAccuracy",
 * String.valueOf(technicalContentandAccuracy)) .param("significanceOfWork",
 * String.valueOf(significanceOfWork)) .param("appropriateForSAC",
 * String.valueOf(appropriateForSAC))) .andExpect(status().isBadRequest());
 * 
 * // Verify service method is not called verify(reviewedservice,
 * never()).updatecomment(paperId, submissionId, comment, originality,
 * relevance, quality, technicalContentandAccuracy, significanceOfWork,
 * appropriateForSAC); } }
 */