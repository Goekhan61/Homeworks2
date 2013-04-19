package de.university.aufgabe4.main;

import java.util.Iterator;

/**
 * @author Anastasia Baron
 * @author Dmitry Petrov
 */

public class List<K> {
	/*
	 * http://www.cs.cmu.edu/~adamchik/15-121/lectures/Linked%20Lists/linked%20lists
	 * .html Some information was taken from
	 * http://docs.oracle.com/javase/7/docs/api/index.html?java/util/List.html
	 */
	private ListNode head;
	private int size;
	private ListNode end;

	// private boolean giltEs = false;

	private class ListNode {
		private ListNode next;
		private K data;

		public ListNode(K elem) {
			this.data = elem;
		}

		public ListNode() {

		}

		private void setData(K data) {
			this.data = data;
		}

		private K getData() {
			return data;
		}

		private void setNext(ListNode next) {
			this.next = next;
		}

		private ListNode getNext() {
			return next;
		}
	}

	public List() {
		head = null; // initialisiert auf null

	}

	/**
	 * Fugt erster element in die liste
	 */
	public void insertFirst(K elem) {
		ListNode newHead = new ListNode();
		newHead.setData(elem);
		newHead.setNext(head);
		head = newHead;
		size++;
	}

	/**
	 * 
	 * return string
	 */
	public String toString() {
		ListNode l = head;
		StringBuilder sb = new StringBuilder();
		while (l != null) {
			sb.append("<" + l.data + ">" + "\n");
			l = l.next;
		}
		return sb.toString();

	}

	/**
	 * Abfragen eines Elements an einer bestimmten Position. Die Methode liefert
	 * das Objekt an der Stelle pos zuruck. Falls gilt (pos < 0 || pos >=
	 * size()) soll ein Objekt der folgenden Runtime-Exception geworfen werden:
	 * java.lang.IndexOutOfBoundsException Inspiration to
	 * http://www.technicalypto.com/2010/01/linked-lists.html
	 * 
	 */
	public K get(int pos) {
		if (pos < 0 || pos >= size()) {
			// giltEs = true;
			throw new IndexOutOfBoundsException();
		}
		ListNode iterating = head;
		for (int i = 0; i < pos && iterating != null; i++, iterating = iterating
				.getNext())
			;
		return iterating.getData();

	}

	/**
	 * Hinzufugen eines Elements an einer bestimmten Position: Die Methode hangt
	 * das Objekt elem an die bestehende Liste hinten an. Falls gilt
	 * (elem==null) soll ein Objekt der folgenden Runtime-Exception geworfen
	 * werden: java.lang.NullPointerException
	 * http://stackoverflow.com/questions/5236486/adding-items-to-end-of-linked-list 
	 * I would like to thank to Pavel
	 * Bennett here
	 * http://www.mycstutorials.com/articles/data_structures/linkedlists
	 * Thank you very much.
	 */
	public void add(K elem) {
		ListNode current = head;
		if (elem == null) { // Is the list empty?
			throw new NullPointerException();
		} else {
			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(new ListNode(elem));
			size++;
		}

	}

	// WICHTIG for Iterator
	private class myIterator implements Iterator<K> {

		public myIterator() {
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public K next() {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * Method to remove the last element retrieved from the linked list; You
		 * don’t want to support this operation so just throw the exception. If
		 * you did support this operation, you would need to include a check
		 * that next() has been called, and if not, throw IllegalStateException
		 * Ivor.Hortons.Beginning.Java.Java.7.Edition
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException(
					"Remove not supported for LinkedList<>");
		}
	}

	public Iterator<K> iterator() {
		return new myIterator();
	}

	/**
	 * Abfragen der Anzahl der Elemente in der Liste: Die Methode gibt die
	 * Anzahl der in der Liste gespeicherten Objekte zuruck.
	 */
	public int size() {
		return this.size;

	}

	/**
	 * Returns true if this list contains no elements.
	 */
	public boolean isEmpty() {
		return head == null;

	}
}
