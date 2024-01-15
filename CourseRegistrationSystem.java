import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int enrolledStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public void enrollStudent() {
        enrolledStudents++;
    }

    public void removeStudent() {
        enrolledStudents--;
    }
}

class Student {
    private int studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (course.getEnrolledStudents() < course.getCapacity()) {
            registeredCourses.add(course);
            course.enrollStudent();
            System.out.println("Course registration successful!");
        } else {
            System.out.println("Course is full. Registration failed.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.removeStudent();
            System.out.println("Course dropped successfully.");
        } else {
            System.out.println("You are not registered for this course.");
        }
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        // Create courses
        Course course1 = new Course("CSE101", "Introduction to Programming", "Basic programming concepts", 30, "Mon-Wed-Fri, 10:00 AM - 11:00 AM");
        Course course2 = new Course("MAT201", "Calculus I", "Limits, derivatives, and integrals", 25, "Tue-Thu, 1:00 PM - 2:30 PM");
        Course course3 = new Course("ENG301", "English Composition", "Writing and communication skills", 20, "Mon-Wed, 2:00 PM - 3:30 PM");

        // Create students
        Student student1 = new Student(1, "John Doe");
        Student student2 = new Student(2, "Jane Smith");

        // Display available courses
        displayCourseListing(course1);
        displayCourseListing(course2);
        displayCourseListing(course3);

        // Student registration
        student1.registerCourse(course1);
        student1.registerCourse(course2);
        student2.registerCourse(course3);

        // Display registered courses for each student
        displayRegisteredCourses(student1);
        displayRegisteredCourses(student2);

        // Student drops a course
        student1.dropCourse(course2);

        // Display updated registered courses for the student
        displayRegisteredCourses(student1);
    }

    private static void displayCourseListing(Course course) {
        System.out.println("\nCourse Code: " + course.getCourseCode());
        System.out.println("Title: " + course.getTitle());
        System.out.println("Description: " + course.getDescription());
        System.out.println("Capacity: " + course.getCapacity());
        System.out.println("Schedule: " + course.getSchedule());
        System.out.println("Enrolled Students: " + course.getEnrolledStudents());
    }

    private static void displayRegisteredCourses(Student student) {
        System.out.println("\nStudent ID: " + student.getStudentID());
        System.out.println("Name: " + student.getName());
        System.out.println("Registered Courses:");

        for (Course course : student.getRegisteredCourses()) {
            System.out.println("- " + course.getTitle());
        }
    }
}
