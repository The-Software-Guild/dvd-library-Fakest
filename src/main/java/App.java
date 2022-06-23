import com.harrybeggs.controller.DvdLibraryController;
import com.harrybeggs.dao.LibraryDao;
import com.harrybeggs.dao.LibraryDaoException;
import com.harrybeggs.dao.LibraryDaoFileImpl;
import com.harrybeggs.ui.DvdLibraryView;
import com.harrybeggs.ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {
        DvdLibraryController controller = new DvdLibraryController();

        DvdLibraryView view = new DvdLibraryView(new UserIOConsoleImpl());
        LibraryDao dao = new LibraryDaoFileImpl();
        try {
            controller.run(view, dao);
        } catch (LibraryDaoException e) {
            view.DisplayErrorMessage(e.getMessage());
        }
    }
}
