package eg.edu.alexu.csd.datastructure.linkedList.cs48;
import java.awt.Point;
import eg.edu.alexu.csd.datastructure.linkedList.IPolynomialSolver;
import eg.edu.alexu.csd.datastructure.linkedList.cs48.SingleLinkedList.Node;
public class PolySolver implements IPolynomialSolver {
 
	private SingleLinkedList[] Polys = new SingleLinkedList[] { new SingleLinkedList(),
			new SingleLinkedList(), new SingleLinkedList(),
			new SingleLinkedList(), new SingleLinkedList(),
			new SingleLinkedList() };
 
	@Override
	public void setPolynomial(char poly, int[][] terms) {
		if (poly != 'A' && poly != 'B' && poly != 'C')
			throw new RuntimeException();
		if (terms[0][1] < 0)
			throw new RuntimeException();
		for (int i = 1; i < terms.length; i++)
			if (terms[i][1] > terms[i - 1][1] || terms[i][1] < 0)
				throw new RuntimeException();
 
		int index = poly - 'A';
		Polys[index].clear();
		Point[] temp = new Point[terms.length];
		for (int i = 0; i < terms.length; i++) {
			// exp then coeff
			temp[i] = new Point(terms[i][1], terms[i][0]);
		}
 
		for (int i = 0; i < terms.length; i++) {
			if (temp[i].y == 0)
				continue;
			Polys[index].add(temp[i]);
		}
	}
 
	@Override
	public String print(char poly) {
		poly = Character.toUpperCase(poly);
		if (poly != 'A' && poly != 'B' && poly != 'C' && poly != 'R')
			return null;
		int index;
		if (poly != 'R')
			index = poly - 'A';
		else
			index = 4;
		if (Polys[index].isEmpty())
			return null;
 
		// Result of add or subtract is 0
		if (((Point) Polys[index].head.getValue()).y == 0)
			return "0";
 
		String result = "";
		Node traverse = Polys[index].head;
		for (int i = 0; i < Polys[index].size(); i++) {
			if (i != 0) {
				if (((Point) traverse.getValue()).y > 0)
					result += "+ ";
				else
					result += "- ";
			} else if (((Point) traverse.getValue()).y < 0)
				result += "-";
 
			if (((Point) traverse.getValue()).y != 1
					&& ((Point) traverse.getValue()).y != -1
					&& ((Point) traverse.getValue()).x != 0)
				result += Integer
						.toString(Math.abs(((Point) traverse.getValue()).y));
 
			if (((Point) traverse.getValue()).x == 0)
				result += Integer
						.toString(Math.abs(((Point) traverse.getValue()).y));
			else if (((Point) traverse.getValue()).x == 1)
				result += "X";
			else {
				result += "X^";
				result += Integer.toString(((Point) traverse.getValue()).x);
			}
 
			if (i != Polys[index].size() - 1)
				result += " ";
			traverse = traverse.getNext();
		}
		return result;
	}
 
	@Override
	public void clearPolynomial(char poly) {
		if (poly != 'A' && poly != 'B' && poly != 'C')
			throw new RuntimeException();
		int index = poly - 'A';
		Polys[index].clear();
	}
 
	@Override
	public float evaluatePolynomial(char poly, float value) {
		if (poly != 'A' && poly != 'B' && poly != 'C' && poly != 'R')
			throw new RuntimeException();
		int index;
		if (poly != 'R')
			index = poly - 'A';
		else
			index = 4;
 
		if (Polys[index].isEmpty())
			throw new RuntimeException();
 
		float result = 0;
		Node traverse = Polys[index].head;
		for (int i = 0; i < Polys[index].size(); i++) {
			result += (float) (((Point) traverse.getValue()).y)
					* Math.pow((double) value,
							(double) ((Point) traverse.getValue()).x);
			traverse = traverse.getNext();
		}
		return result;
	}
 
	@Override
	public int[][] add(char poly1, char poly2) {
		if (poly2 == 'D' && Polys[3].isEmpty())
			throw new RuntimeException();
		if (poly1 == 'E' && Polys[5].isEmpty())
			throw new RuntimeException();
		if (poly1 != 'A' && poly1 != 'B' && poly1 != 'C' && poly1 != 'E')
			throw new RuntimeException();
		if (poly2 != 'A' && poly2 != 'B' && poly2 != 'C' && poly2 != 'D')
			throw new RuntimeException();
 
		int index1;
		int index2 = poly2 - 'A';
		if (poly1 != 'E')
			index1 = poly1 - 'A';
		else
			index1 = 5;
 
		if (Polys[index1].isEmpty() || Polys[index2].isEmpty())
			throw new RuntimeException();
		Node pointer1 = Polys[index1].head;
		Node pointer2 = Polys[index2].head;
 
		Polys[4].clear();
 
		for (int i = 0; i < Polys[index1].size() + Polys[index2].size(); i++) {
			if (pointer1 == null) {
				while (pointer2 != null) {
					Polys[4].add(pointer2.getValue());
					pointer2 = pointer2.getNext();
				}
				break;
			} else if (pointer2 == null) {
				while (pointer1 != null) {
					Polys[4].add(pointer1.getValue());
					pointer1 = pointer1.getNext();
				}
				break;
			}
 
			if (((Point) pointer1.getValue()).x > ((Point) pointer2.getValue()).x) {
				Polys[4].add(pointer1.getValue());
				pointer1 = pointer1.getNext();
			} else if (((Point) pointer1.getValue()).x < ((Point) pointer2.getValue()).x) {
				Polys[4].add(pointer2.getValue());
				pointer2 = pointer2.getNext();
			} else {
				Point sumPoints = new Point(((Point) pointer1.getValue()).x,
						((Point) pointer1.getValue()).y + ((Point) pointer2.getValue()).y);
				if (sumPoints.y != 0)
					Polys[4].add(sumPoints);
				pointer1 = pointer1.getNext();
				pointer2 = pointer2.getNext();
				i++;
			}
		}
		if (Polys[4].size() == 0)
			Polys[4].add(new Point(0, 0));
 
		int[][] result = new int[Polys[4].size()][2];
		Node temp = Polys[4].head;
		for (int i = 0; i < result.length; i++) {
			result[i][0] = ((Point) temp.getValue()).y;
			result[i][1] = ((Point) temp.getValue()).x;
			temp = temp.getNext();
		}
 
		return result;
	}
 
	@Override
	public int[][] subtract(char poly1, char poly2) {
		if (poly1 != 'A' && poly1 != 'B' && poly1 != 'C')
			throw new RuntimeException();
		if (poly2 != 'A' && poly2 != 'B' && poly2 != 'C')
			throw new RuntimeException();
 
		int index1 = poly1 - 'A';
		int index2 = poly2 - 'A';
		if (Polys[index1].isEmpty() || Polys[index2].isEmpty())
			throw new RuntimeException();
 
		// Copying A into D
		Node copier = Polys[index2].head;
		while (copier != null) {
			Point newPoint = new Point(((Point) copier.getValue()).x,
					((Point) copier.getValue()).y);
			Polys[3].add(newPoint);
			copier = copier.getNext();
		}
 
		// Reverting Signs of D
		Node traverse = Polys[3].head;
		for (int i = 0; i < Polys[3].size(); i++) {
			((Point) traverse.getValue()).y *= -1;
			traverse = traverse.getNext();
		}
 
		int[][] returnVal = add(poly1, 'D');
		Polys[3].clear();
		return returnVal;
	}
 
	@Override
	public int[][] multiply(char poly1, char poly2) {
		if (poly1 != 'A' && poly1 != 'B' && poly1 != 'C')
			throw new RuntimeException();
		if (poly2 != 'A' && poly2 != 'B' && poly2 != 'C')
			throw new RuntimeException();
 
		int index1 = poly1 - 'A';
		int index2 = poly2 - 'A';
		if (Polys[index1].isEmpty() || Polys[index2].isEmpty())
			throw new RuntimeException();
 
		Polys[4].clear();
		Node pointer1 = Polys[index1].head;
		Node pointer2 = Polys[index2].head;
 
		// 1st term * 2nd poly
		while (pointer2 != null) {
			Point multResult = new Point(((Point) pointer1.getValue()).x
					+ ((Point) pointer2.getValue()).x, ((Point) pointer1.getValue()).y
					* ((Point) pointer2.getValue()).y);
			Polys[3].add(multResult);
			pointer2 = pointer2.getNext();
		}
		// Copying D into R
		Node copier = Polys[3].head;
		while (copier != null) {
			Point newPoint = new Point(((Point) copier.getValue()).x,
					((Point) copier.getValue()).y);
			Polys[4].add(newPoint);
			copier = copier.getNext();
		}
		pointer1 = pointer1.getNext();
		pointer2 = Polys[index2].head;
 
		while (pointer1 != null) {
			while (pointer2 != null) {
				Point multResult = new Point(((Point) pointer1.getValue()).x
						+ ((Point) pointer2.getValue()).x,
						((Point) pointer1.getValue()).y * ((Point) pointer2.getValue()).y);
				Polys[5].add(multResult);
				pointer2 = pointer2.getNext();
			}
			add('E', 'D');
			Polys[3].clear();
			Polys[5].clear();
 
			// Copying R into D
			Node copier2 = Polys[4].head;
			while (copier2 != null) {
				Point newPoint = new Point(((Point) copier2.getValue()).x,
						((Point) copier2.getValue()).y);
				Polys[3].add(newPoint);
				copier2 = copier2.getNext();
			}
 
			pointer2 = Polys[index2].head;
			pointer1 = pointer1.getNext();
		}
 
		Polys[3].clear();
		Polys[5].clear();
 
		int[][] result = new int[Polys[4].size()][2];
		Node temp = Polys[4].head;
		for (int i = 0; i < result.length; i++) {
			result[i][0] = ((Point) temp.getValue()).y;
			result[i][1] = ((Point) temp.getValue()).x;
			temp = temp.getNext();
		}
 
		return result;
	}
 
}
 