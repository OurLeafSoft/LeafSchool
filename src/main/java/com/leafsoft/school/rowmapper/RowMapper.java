package com.leafsoft.school.rowmapper;

import java.math.BigInteger;
import java.util.Map;

import com.leafsoft.school.model.OrgDetail;
import com.leafsoft.school.model.OrgUser;
import com.leafsoft.school.model.OrgUserRole;
import com.leafsoft.school.model.StudentDetail;

public class RowMapper {

	public static OrgUser getOrgUserRow(Map<String, Object> row) {
		OrgUser orguser = new OrgUser();
		orguser.setCreatetime(row.get("createdtime")!= null ? BigInteger.valueOf((long) row.get("createdtime")) : new BigInteger("0"));
		orguser.setDefaultorgid(row.get("defaultorgid") != null ? (Integer)row.get("defaultorgid") : -1);
		orguser.setEmail(row.get("email")!=null ? (String)row.get("email") : "");
		orguser.setLid(row.get("lid") != null ? (Integer)row.get("lid") : -1);
		orguser.setLuid(row.get("luid") != null ? (Integer)row.get("luid") : -1);
		orguser.setUsername(row.get("username")!= null ? (String)row.get("username") : "");
		return orguser;
	}
	
	public static OrgDetail getOrgDetailRow(Map<String, Object> row) {
		OrgDetail orgdetail = new OrgDetail();
		orgdetail.setAddress(row.get("address") != null ? (String)row.get("address") : "");
		orgdetail.setCity(row.get("city")!= null ? (String)row.get("city") : "");
		orgdetail.setCountry(row.get("country") !=null ? (String)row.get("country") : "");
		orgdetail.setCreatedtime(row.get("createdtime") != null ? BigInteger.valueOf((long) row.get("createdtime")) : new BigInteger("-1"));
		orgdetail.setCurrencycode(row.get("currencycode") != null ? (String)row.get("currencycode") : "");
		orgdetail.setDateformat(row.get("dateformat") != null ? (String)row.get("dateformat") : "");
		orgdetail.setOrgid(row.get("orgid") !=null ? (Integer)row.get("orgid") : -1);
		orgdetail.setOrgname(row.get("orgname") != null ? (String)row.get("orgname") : "");
		orgdetail.setState(row.get("state") !=null ? (String)row.get("state") : "");
		orgdetail.setStatus(row.get("state") != null ? Byte.valueOf(row.get("status").toString()) : 0);
		orgdetail.setTimetype(row.get("timetype")!=null ? (String)row.get("timetype") : "GMT");
		orgdetail.setZipcode(row.get("zipcode") !=null ? (String)row.get("zipcode") : "");
		return orgdetail;
	}
	
	public static OrgUserRole getOrgUserRoleRow(Map<String, Object> row) {
		OrgUserRole orguserrole = new OrgUserRole();
		orguserrole.setRolename(row.get("rolename")!=null ? (String)row.get("rolename") : "");
		orguserrole.setUserRoleId(row.get("user_role_id") != null ? (Integer)row.get("user_role_id") : -1);
		return orguserrole;
	}
	
}


/*class FullDiaryRowCallbackHandler implements RowCallbackHandler {
    private Collection<Diary> diaries = new ArrayList<Diary>();
    private Diary currentDiary = null;
    private Page currentPage = null;

    public void processRow(ResultSet rs) {
       long diaryId = rs.getLong("d.id");
       if (currentDiary == null || diaryId != currentDiary.getId()) {
          currentDiary = new Diary();
          currentPage = null;
          diaries.add(currentDiary);
          currentDiary.setId(diaryId);
          currentDiary.setCreationDate(toLocalTime(rs.getTimestamp("d.creationDate")));
          currentDiary.setDescription(rs.getString("d.description"));
          ...
       }
       long pageId = rs.getLong("p.id");
       if (!rs.wasNull() && currentPage != null && currentPage.getId() != pageId) {
          currentPage = new Page();
          if (currentDiary.getPages() == null) {
              currentDiary.setPages(new ArrayList<Page>());
          }
          currentDiary.getPages().add(currentPage);
          currentPage.setId(pageId);
          currentPage.setCreationDate(toLocalTime(rs.getTimestamp("p.creationDate")));
          ...
       }
       long commentId = rs.getLong("c.id");
       if (!rs.wasNull() && currentPage != null) {
          Comment comment = new Comment();
          if (currentPage.getComments() == null) {
              currentPage.setComments(new ArrayList<Comment>());
          }
          currentPage.getComments().add(comment);
          comment.setId(commentId);
          comment.setPostingDate(toLocalTime(rs.getTimestamp("c.postingDate")));
          comment.setComment(rs.getString("c.comment"));
       }
    }

    public Collection<Diary> getDiaries() {
       return diaries;
    }
 }

 FullDiaryRowCallbackHandler rowCallbackHandler = new FullDiaryRowCallbackHandler();
 Collection<Diary> result = jdbcTemplate.query(
    "select d.id, " +
           "d.creationDate, " +
           "d.description, " +
           "p.id, " +
           "p.creationDate, " +
           "c.id, " +
           "c.postingDate, " +
           "c.comment " +
      "from Diary d " +
      "left outer join Page p on d.id = p.diary " +
      "left outer join Comment c on p.id = c.page " +
     "where d.member = ? " +
     "order by d.id, p.id, c.id",
    rowCallbackHandler,
    myMemberId);
Collection<Diary> diariesForMember = rowCallbackHandler.getDiaries();*/