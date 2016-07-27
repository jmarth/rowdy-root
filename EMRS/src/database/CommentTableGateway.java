package database;

import java.util.List;

import models.Allergy;
import models.Comment;
import models.Patient;
import models.Vitals;

public interface CommentTableGateway {
	public abstract List<Comment> fetchComments() throws GatewayException;
	public List<Comment> fetchCommentsForPatient(Patient p) throws GatewayException;
	public List<Comment> fetchCommentsForPatientByType(Patient p, int t, long tid) throws GatewayException;
	public long insertComment(Comment c) throws GatewayException;
	public void updateComment(Comment c) throws GatewayException;
	public void removeComment(Long cid) throws GatewayException;
}
