package controllers;

import entities.Task;
import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import entities.Participant;
import entities.User;
import facades.TaskFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("taskController")
@SessionScoped
public class TaskController implements Serializable {

    private Task current;
    private DataModel items = null;
    @EJB
    private facades.TaskFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public TaskController() {
    }

    //DEPRECATED
    public Date getToDayDate() {
        Date date = new Date();
        return date;
    }

    public Task getSelected() {
        if (current == null) {
            current = new Task();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TaskFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Task) getItems().getRowData();

        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareView2(Task task) {
        current = task;
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Task();
        selectedItemIndex = -1;
        return "Create";
    }

    public void setHidden(User user) {
        current.setFkCreator(user);
    }

    public void create() throws Exception {
        getFacade().create(current);
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TaskCreated"));
    }

    public String createFromAdmin() {
        try {
            create();
            return "/admin/participant/List.xhtml";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String createFromUser() {
        try {
            create();
            return "/participant/List.xhtml";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Task) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String prepareEdit2(Task task) {
        current = task;
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public void update() throws Exception {
        getFacade().edit(current);
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TaskUpdated"));
    }

    public String updateFromAdmin() {
        try {
            update();
            return "/admin/participant/List.xhtml";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String updateFromUser() {
        try {
            update();
            return "/participant/List.xhtml";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Task) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroy2(Task task) {
        current = task;
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TaskDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Task getTask(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Task.class)
    public static class TaskControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TaskController controller = (TaskController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "taskController");
            return controller.getTask(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Task) {
                Task o = (Task) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Task.class.getName());
            }
        }

    }
    
    public boolean checkObservator (User user, Task task) {
        if(Objects.equals(task.getFkCreator().getId(), user.getId())) {
            return true;
        } else {
            for(Participant p : task.getParticipantCollection()) {
                if (Objects.equals(p.getIdUser().getId(), user.getId())) {
                    if(!p.getObservator()) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }

}
