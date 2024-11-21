package edu.umn.cs.csci3081w.project.model;

/**
 * StorageFacility. contains storage of Bus/Train Vehicle types.
 *
 */
public class StorageFacility {
  private int largeBusesNum;
  private int smallBusesNum;
  private int electricTrainsNum;
  private int dieselTrainsNum;

  /**
   * Constructor of StorageFacility. sets all variables to zero.
   */
  public StorageFacility() {
    largeBusesNum = 0;
    smallBusesNum = 0;
    electricTrainsNum = 0;
    dieselTrainsNum = 0;
  }


  /**
   * Constructor of StorageFacility.
   * @param largeBusesNum largeBusesNum.
   * @param smallBusesNum smallBusesNum.
   * @param electricTrainsNum electricTrainsNum.
   * @param dieselTrainsNum dieselTrainsNum.
   */
  public StorageFacility(int largeBusesNum, int smallBusesNum,
                         int electricTrainsNum, int dieselTrainsNum) {
    this.largeBusesNum = largeBusesNum;
    this.smallBusesNum = smallBusesNum;
    this.electricTrainsNum = electricTrainsNum;
    this.dieselTrainsNum = dieselTrainsNum;
  }

  /**
   * returns LargeBusesNumb.
   * @return LargeBusesNum as int.
   */
  public int getLargeBusesNum() {
    return largeBusesNum;
  }

  /**
   * returns SmallBusesNum.
   * @return SmallBusesNum as int.
   */
  public int getSmallBusesNum() {
    return smallBusesNum;
  }

  /**
   * returns ElectricTrainsNum.
   * @return ElectricTrainsNum as int.
   */
  public int getElectricTrainsNum() {
    return electricTrainsNum;
  }

  /**
   * returns DieselTrainsNum.
   * @return DieselTrainsNum as int.
   */
  public int getDieselTrainsNum() {
    return dieselTrainsNum;
  }

  /**
   * set largeBusesNum.
   * @param largeBusesNum LargeBusesBum.
   */
  public void setLargeBusesNum(int largeBusesNum) {
    this.largeBusesNum = largeBusesNum;
  }

  /**
   * set smallBusesNum.
   * @param smallBusesNum smallBusesNum.
   */
  public void setSmallBusesNum(int smallBusesNum) {
    this.smallBusesNum = smallBusesNum;
  }

  /**
   * set electricTrainsNum.
   * @param electricTrainsNum ElectricTrainNums.
   */
  public void setElectricTrainsNum(int electricTrainsNum) {
    this.electricTrainsNum = electricTrainsNum;
  }

  /**
   * set dieselTrainsNum.
   * @param dieselTrainsNum dieselTrainsNum.
   */
  public void setDieselTrainsNum(int dieselTrainsNum) {
    this.dieselTrainsNum = dieselTrainsNum;
  }

  /**
   * decrement Large bus numbers.
   */
  public void decrementLargeBusesNum() {
    largeBusesNum--;
  }

  /**
   * decrement small bus numbers.
   */
  public void decrementSmallBusesNum() {
    smallBusesNum--;
  }

  /**
   * decrement electric train numbers.
   */
  public void decrementElectricTrainsNum() {
    electricTrainsNum--;
  }

  /**
   * decrement diesel train numbers.
   */
  public void decrementDieselTrainsNum() {
    dieselTrainsNum--;
  }

  /**
   * increment large bus numbers.
   */
  public void incrementLargeBusesNum() {
    largeBusesNum++;
  }

  /**
   * increment small bus numbers.
   */
  public void incrementSmallBusesNum() {
    smallBusesNum++;
  }

  /**
   * increment electric train numbers.
   */
  public void incrementElectricTrainsNum() {
    electricTrainsNum++;
  }

  /**
   * increment diesel train numbers.
   */
  public void incrementDieselTrainsNum() {
    dieselTrainsNum++;
  }
}
