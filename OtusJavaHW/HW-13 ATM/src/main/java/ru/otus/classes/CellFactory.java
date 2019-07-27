package ru.otus.classes;

import java.util.SortedSet;

import ru.otus.interfaces.ICell;

public abstract class CellFactory {
	abstract SortedSet<ICell> createCellSet();
}
