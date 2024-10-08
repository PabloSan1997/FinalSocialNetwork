
import { Provider } from "react-redux";
import { MainRoutes } from "./MainRotes";
import { store } from "./store/store";
import './styles/index.scss';


function App() {

  return (
    <Provider store={store}>
      <MainRoutes />
    </Provider>

  );
}

export default App
