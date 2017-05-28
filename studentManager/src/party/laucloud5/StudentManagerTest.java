package party.laucloud5;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagerTest {
	public static void main(String[] args) {
		ArrayList<Student> arr = new ArrayList<Student>();
		
	   /* while (true) { 
		   System.out.println("--欢迎登陆学生管理系统--"); 
		   Scanner sc = new Scanner(System.in); 
		   System.out.print("用户名:"); 
		   String user = sc.nextLine(); 
		   System.out.print("密码:"); 
		   String password = sc.nextLine(); 
		   if (!(user.isEmpty()) && !(password.isEmpty())) { 
			   if(user.equals("root") && password.equals("123")) {
			 	  System.out.println("恭喜您！登陆成功！"); break; 
			   } else {
			 	  System.out.println("用户名或密码错误！请重新输入！！"); 
			   } 
		 } else {
		 	System.out.println("用户名或密码不能为空！请重新输入！！");
	     } 
	   }*/
		 
		for (int i = 0; i < 3; i++) {
			System.out.println("--欢迎登陆学生管理系统--");
			Scanner sc = new Scanner(System.in);
			System.out.print("用户名:");
			String user = sc.nextLine();
			System.out.print("密码:");
			String password = sc.nextLine();
			if (!(user.isEmpty()) && !(password.isEmpty())) {
				if (user.equals("root") && password.equals("123")) {
					System.out.println("恭喜您！登陆成功！");
					break;
				} else {
					if (2 - i == 0) {
						System.out.println("用户名或密码错误！");
						System.out.println("抱歉！您的机会已用完,账号已被锁定，请与管理员联系！");
						System.exit(0);
					} else {
						System.out.println("用户名或密码错误！请重新输入！！");
						System.out.println("您还有" + (2 - i) + "次机会！");
					}
				}
			} else {
				if (2 - i == 0) {
					System.out.println("用户名或密码不能为空！");
					System.out.println("抱歉！您的机会已用完,账号已被锁定，请与管理员联系！");
					System.exit(0);
				} else {
					System.out.println("用户名或密码不能为空！请重新输入！！");
					System.out.println("您还有" + (2 - i) + "次机会！");
				}
			}
		}

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
				// 显示所有学生信息
				findAllStudent(arr);
				break;
			case "2":
				// 增加学生信息
				addStudent(arr);
				break;
			case "3":
				// 删除学生信息
				deleteStudent(arr);
				break;
			case "4":
				// 修改学生信息
				updateStudent(arr);
				break;
			case "5":
				// 退出
			default:
				System.out.println("谢谢您的使用！");
				System.exit(0);
				break;
			}
		}
	}

	public static void findAllStudent(ArrayList<Student> arr) {
		if (arr.size() == 0) {
			System.out.println("抱歉！暂时还没有录入学生信息！");
			return;
		}
		System.out.println("学号\t姓名\t年龄\t居住地");
		for (int i = 0; i < arr.size(); i++) {
			Student s = arr.get(i);
			System.out.println(s.getId() + "\t" + s.getName() + "\t"
					+ s.getAge() + "\t" + s.getAddress());
		}
	}

	public static void addStudent(ArrayList<Student> arr) {
		Scanner s1 = new Scanner(System.in);
		String id;
		while (true) {
			System.out.println("请输入学生学号:");
			id = s1.nextLine();
			boolean flag = false;
			for (int i = 0; i < arr.size(); i++) {
				Student s = arr.get(i);
				if (s.getId().equals(id)) {
					flag = true;
					break;
				}
			}
			if (flag) {
				System.out.println("该学号" + id + "已存在，请重新输入！");
			} else {
				break;
			}
		}
		System.out.println("请输入学生姓名:");
		String name = s1.nextLine();
		System.out.println("请输入学生年龄 :");
		String age = s1.nextLine();
		System.out.println("请输入学生居住地:");
		String address = s1.nextLine();
		// s1.close();
		Student s2 = new Student(id, name, age, address);
		arr.add(s2);
		System.out.println("增加学生信息成功！");
	}

	public static void deleteStudent(ArrayList<Student> arr) {
		Scanner s2 = new Scanner(System.in);
		System.out.println("请输入要删除学生的学号:");
		String id = s2.nextLine();
		int index = -1;
		for (int i = 0; i < arr.size(); i++) {
			Student s = arr.get(i);
			if (s.getId().equals(id)) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			System.out.println("该学号" + id + "没有对应的学生信息,请重新选择操作！");
		} else {
			arr.remove(index);
			System.out.println("删除学生信息成功！");
		}
	}
	
	public static void updateStudent(ArrayList<Student> arr) {
		Scanner s3 = new Scanner(System.in);
		System.out.println("请输入要修改学生的学号:");
		String id = s3.nextLine();
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
			String name = s3.nextLine();
			System.out.println("请输入新年龄:");
			String age = s3.nextLine();
			System.out.println("请输入新居住地:");
			String address = s3.nextLine();

			Student s = new Student(id, name, age, address);
			arr.set(index, s);
			System.out.println("修改学生信息成功！");
		}
	}
}