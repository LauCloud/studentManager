package party.laucloud;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagerTest {
	public static void main(String[] args) {
		ArrayList<Student> arr = new ArrayList<Student>();

		while (true) {
			System.out.println("-----欢迎使用学生管理系统-----");
			System.out.println("1.查看所有学生信息");
			System.out.println("2.添加学生");
			System.out.println("3.删除学生");
			System.out.println("4.修改学生");
			System.out.println("5.退出");
			System.out.print("请输入您的选择:");
			Scanner sc = new Scanner(System.in);
			String choice = sc.nextLine();
			switch (choice) {
			case "1":
				findAllStudent(arr);
				break;
			case "2":
				addStudent(arr);
				break;
			case "3":
				deleteStudent(arr);
				break;
			case "4":
				updateStudent(arr);
				break;
			case "5":
				// break;
			default:
				System.out.println("谢谢您的使用");
				System.exit(0);
				break;
			}
		}
	}

	public static void findAllStudent(ArrayList<Student> arr) {
		if (arr.size() == 0) {
			System.out.println("不好意思，目前还没有学生信息供查询，请您重新选择！");
		} else {
			System.out.println("学号\t姓名\t年龄\t居住地");
			for (int i = 0; i < arr.size(); i++) {
				Student s = arr.get(i);
				System.out.println(s.getId() + "\t" + s.getName() + "\t"
						+ s.getAge() + "\t" + s.getAddress());
			}
		}
	}

	public static void addStudent(ArrayList<Student> arr) {
		Scanner sc = new Scanner(System.in);
		String id;
		while (true) {
			System.out.println("请输入学生学号:");
			id = sc.nextLine();
			boolean flag = false;
			for (int i = 0; i < arr.size(); i++) {
				Student stu = arr.get(i);
				if (stu.getId().equals(id)) {
					flag = true;
					break;
				}
			}
			if (flag) {
				System.out.println("不好意思，该学号已存在！请重新输入！");
			} else {
				break;
			}
		}
		System.out.println("请输入学生姓名:");
		String name = sc.nextLine();
		System.out.println("请输入学生年龄:");
		String age = sc.nextLine();
		System.out.println("请输入学生居住地:");
		String address = sc.nextLine();

		Student s = new Student();
		s.setId(id);
		s.setName(name);
		s.setAge(age);
		s.setAddress(address);

		arr.add(s);
		System.out.println("添加学生成功！");
	}

	public static void deleteStudent(ArrayList<Student> arr) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入要删除学生的学号:");
		String id = sc.nextLine();
		int index = -1;
		for (int i = 0; i < arr.size(); i++) {
			Student s = arr.get(i);
			if (s.getId().equals(id)) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			arr.remove(index);
			System.out.println("删除学生成功！");
		} else {
			System.out.println("该学号" + id + "没有对应的学生信息，请重新选择操作！");
		}
	}

	public static void updateStudent(ArrayList<Student> arr) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入您要修改的学生学号:");
		String id = sc.nextLine();
		int index = -1;
		for (int i = 0; i < arr.size(); i++) {
			Student s = arr.get(i);
			if (s.getId().equals(id)) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			System.out.println("该学号" + id + "没有对应的学生信息，请重新选择操作！");
		} else {
			System.out.println("请输入新姓名:");
			String name = sc.nextLine();
			System.out.println("请输入新年龄:");
			String age = sc.nextLine();
			System.out.println("请输入新居住地:");
			String address = sc.nextLine();
			Student s = new Student();
			s.setId(id);
			s.setName(name);
			s.setAge(age);
			s.setAddress(address);

			arr.set(index, s);
			System.out.println("修改学生成功！");
		}
	}
}