package exercise.android.reemh.todo_items;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TodoItemsHolderImplTest{

  @Test
  public void when_addingTodoItem_then_callingListShouldHaveThisItem(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");

    // verify
    assertEquals(1, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void when_addingTwoTodoItem_then_callingListShouldHaveTheseItems(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("finish ex5");

    // verify
    assertEquals(2, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void when_addingLotOfTodoItem_then_callingListShouldHaveTheseItems(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    int counter = 1;
    for (int i = 0; i < 30; i++)
    {
      holderUnderTest.addNewInProgressItem("Task number "+ counter);
      counter++;
    }

    // verify
    assertEquals(30, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void when_deletingOntTodoItem_then_callingListShouldHaveOneLessItem(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    int counter = 1;
    for (int i = 0; i < 30; i++)
    {
      holderUnderTest.addNewInProgressItem("Task number "+ counter);
      counter++;
    }

    holderUnderTest.deleteItem(holderUnderTest.getCurrentItems().get(0));
    // verify
    assertEquals(29, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void when_deletingTwoTodoItemFromTwo_then_callingListShouldHaveNoItems(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("finish ex5");

    holderUnderTest.deleteItem(holderUnderTest.getCurrentItems().get(0));
    holderUnderTest.deleteItem(holderUnderTest.getCurrentItems().get(0));

    // verify
    assertEquals(0, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void when_changingItemFromInProgressToDone_then_itChangesToDone(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));

    // verify
    assertEquals(holderUnderTest.getCurrentItems().get(0).getStatus(), Status.DONE);
  }

  @Test
  public void when_changingItemFromInProgressToInProgress_then_itChangesNothing(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.markItemInProgress(holderUnderTest.getCurrentItems().get(0));

    // verify
    assertEquals(holderUnderTest.getCurrentItems().get(0).getStatus(), Status.IN_PROGRESS);
  }

  @Test
  public void when_changingItemFromDoneToDone_then_itChangesNothing(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));

    // verify
    assertEquals(holderUnderTest.getCurrentItems().get(0).getStatus(), Status.DONE);
  }

  @Test
  public void when_changingItemFromDoneToInProgress_then_itChangesToInProgress(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.markItemInProgress(holderUnderTest.getCurrentItems().get(0));
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));
    holderUnderTest.markItemInProgress(holderUnderTest.getCurrentItems().get(0));

    // verify
    assertEquals(holderUnderTest.getCurrentItems().get(0).getStatus(), Status.IN_PROGRESS);
  }

  @Test
  public void when_changingItemDescription_then_itsDescriptionChanges(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.getCurrentItems().get(0).setDescription("finish Ex5");

    // verify
    assertEquals(holderUnderTest.getCurrentItems().get(0).getDescription(), "finish Ex5");
  }

  @Test
  public void when_clearsItemDescription_then_itsDescriptionClears(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.getCurrentItems().get(0).setDescription("");

    // verify
    assertEquals(holderUnderTest.getCurrentItems().get(0).getDescription(), "");
  }
}