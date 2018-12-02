/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package err;

/**
 *
 * @author nhatc
 */
public class DashBoardError {

    private String deleteError = "abc";
    private String updateError = "";

    public String getDeleteError() {
        return deleteError;
    }

    public void setDeleteError(String deleteError) {
        this.deleteError = deleteError;
    }

    public String getUpdateError() {
        return updateError;
    }

    public void setUpdateError(String updateError) {
        this.updateError = updateError;
    }

    public DashBoardError() {
    }

}
