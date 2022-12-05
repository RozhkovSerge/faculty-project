package entity;

import java.util.Date;

public class Archive {

    private Long id;

    private Long userId;

    private Long courseId;

    private int mark;

    private Date dateOfExam;

    public Archive(Long id, Long userId, Long courseId, int mark, Date dateOfExam) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.mark = mark;
        this.dateOfExam = dateOfExam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Date getDateOfExam() {
        return dateOfExam;
    }

    public void setDateOfExam(Date dateOfExam) {
        this.dateOfExam = dateOfExam;
    }

    @Override
    public String toString() {
        return "Archive{" +
                "id=" + id +
                ", userId=" + userId +
                ", courseId=" + courseId +
                ", mark=" + mark +
                ", dateOfExam=" + dateOfExam +
                '}';
    }
}
