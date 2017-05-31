package party.laucloud;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagerTest {
	public final static String fileName = "Students.txt";
	
	public static void main(String[] args) throws IOException {
		ArrayList<Student> arr = new ArrayList<Student>();
		
		
		for (int i = 0; i < 3; i++) {
			System.out.println("---欢迎登陆学生管理系统---");
			Scanner sc = new Scanner(System.in);
			System.out.print("用户名:");
			String user = sc.nextLine();
			System.out.print("密码:");
			String password = sc.nextLine();
			if (!(user.isEmpty()) && !(password.isEmpty())) {
				if (user.equals("root") && password.equals("123")) {
					System.out.println("恭喜您！登陆成功!");
					break;
				} else {
					if (2 - i == 0) {
						System.out.println("用户名或密码错误！请重新输入！");
						System.out.println("该账号已被锁定！请与管理员联系！");
						System.exit(0);
					} else {
						System.out.println("用户名或密码错误！请重新输入！");
						System.out.println("您还有" + (2 - i) + "次机会！");
					}
				}
			} else {
				if (2 - i == 0) {
					System.out.println("用户名或密码不能为空！请重新输入！");
					System.out.println("该账号已被锁定！请与管理员联系！");
					System.exit(0);
				} else {
					System.out.println("用户名或密码不能为空！请重新输入！");
					System.out.println("您还有" + (2 - i) + "次机会！");
				}
			}
		}
		
		readData(fileName, arr);

		while (true) {
			System.out.println("-----欢迎进入学生管理系统-----");
			System.out.println("1.显示所有学生信息");
			System.out.println("2.增加学生信息");
			System.out.println("3.删除学生信息");
			System.out.println("4.修改学生信息");
			System.out.println("5.退出");
			System.out.print("请输入您的选择:");
			Scanner sc = new Scanner(System.in);
			String choice = sc.nextLine();
			switch (choice) {
			case "1":
				showAllStudent(arr);
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
			default:
				System.out.println("谢谢您的使用！");
				System.exit(0);
				break;
			}
		}
	}
	
	//从文本文件中将数据读取到集合中
	public static void readData(String fileName, ArrayList<Student> arr)
			throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));

		String line;
		while ((line = br.readLine()) != null) {
			String[] strArr = line.split(",");
			Student s = new Student();
			s.setId(strArr[0]);
			s.setName(strArr[1]);
			s.setAge(strArr[2]);
			s.setAddress(strArr[3]);
			arr.add(s);
		}
		br.close();
	}
	
	//将集合中的数据写入到文本文件中
	public static void writeData(String fileName, ArrayList<Student> arr)
			throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

		for (int i = 0; i < arr.size(); i++) {
			Student s = arr.get(i);
			StringBuilder sb = new StringBuilder();
			sb.append(s.getId() + "," + s.getName() + "," + s.getAge() + ","
					+ s.getAddress());
			bw.write(sb.toString());
			bw.newLine();
			bw.flush();
		}
		bw.close();
	}

	public static void showAllStudent(ArrayList<Student> arr) {
		if (arr.size() == 0) {
			System.out.println("抱歉！还没有录入学生信息，请重新选择操作！");
			return;
		}
		System.out.println("学号\t姓名\t年龄\t居住地");
		for (int i = 0; i < arr.size(); i++) {
			Student s = arr.get(i);
			System.out.println(s.getId() + "\t" + s.getName() + "\t"
					+ s.getAge() + "\t" + s.getAddress());
		}
	}

	public static void addStudent(ArrayList<Student> arr) throws IOException {
		Scanner sc = new Scanner(System.in);
		String id;
		while (true) {
			System.out.println("请输入学生学号:");
			id = sc.nextLine();
			if (id.isEmpty()) {
				System.out.println("学号不能为空，请重新输入!");
			} else {
				boolean flag = false;
				for (int i = 0; i < arr.size(); i++) {
					Student s = arr.get(i);
					if (s.getId().equals(id)) {
						flag = true;
						break;
					}
				}
				if (flag) {
					System.out.println("该学号" + id + "已存在对应的学生信息，请重新输入!");
				} else {
					break;
				}
			}
		}

		System.out.println("请输入学生姓名:");
		String name = sc.nextLine();
		System.out.println("请输入学生年龄:");
		String age = sc.nextLine();
		System.out.println("请输入学生居住地:");
		String address = sc.nextLine();

		Student s = new Student(id, name, age, address);
		arr.add(s);
		writeData(fileName, arr);
		System.out.println("学生信息添加成功！");
	}

	public static void deleteStudent(ArrayList<Student> arr) throws IOException {
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
		if (index == -1) {
			System.out.println("该学号" + id + "对应的学生信息不存在，请选择其它操作！");
		} else {
			arr.remove(index);
			writeData(fileName, arr);
			System.out.println("删除学生信息成功!");
		}
	}

	public static void updateStudent(ArrayList<Student> arr) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入要修改学生的学号:");
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
			System.out.println("该学号" + id + "对应的学生信息不存在，请重新选择操作！");
		} else {
			System.out.println("请输入新姓名:");
			String name = sc.nextLine();
			System.out.println("请输入新年龄:");
			String age = sc.nextLine();
			System.out.println("请输入新居住地:");
			String address = sc.nextLine();

			Student s = new Student(id, name, age, address);
			arr.set(index, s);
			writeData(fileName, arr);
			System.out.println("修改学生信息成功！");
		}
	}
}