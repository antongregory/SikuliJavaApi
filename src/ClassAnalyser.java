
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.io.File;
import java.io.IOException;

public class ClassAnalyser {

	public static void listMethodCalls(File file) {
		{
			// System.out.println(path);
			try {
				new VoidVisitorAdapter<Object>() {
					@Override
					public void visit(MethodCallExpr n, Object arg) {
						super.visit(n, arg);
						
						System.out.println(" [L " + n.getBeginLine() + "] " + n);
					}

					@Override
					public void visit(MethodDeclaration n, Object arg1) {
						System.out.println("vistied"+n.getDeclarationAsString());
					};
				}.visit(JavaParser.parse(file), null);
				System.out.println(); // empty line
			} catch (ParseException | IOException e) {
				new RuntimeException(e);
			}
		}
	}

	public static void main(String[] args) {
		File projectDir = new File("C:\\Users\\Anton\\Documents\\Thesis\\Visualizing UI Impact\\src\\Sample.java");
		System.out.println("asd");
		listMethodCalls(projectDir);
	}
}