package ru.otus.classes;

import java.util.SortedSet;
import java.util.TreeSet;

import ru.otus.interfaces.ICell;

public class DefaultCellFactory extends CellFactory{

	@Override
	public SortedSet<ICell> createCellSet() {
		
		SortedSet<ICell> cells = new TreeSet<ICell>((c1, c2) -> {return c2.getFaceValues() - c1.getFaceValues();});
		
		for (FaceValues fv : FaceValues.values()) {
			cells.add(new Cell(fv, 0));
		}
		
		return cells;
	}

}
