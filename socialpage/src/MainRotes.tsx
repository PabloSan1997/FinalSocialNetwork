import { HashRouter, useRoutes } from "react-router-dom";
import { LoginRegister } from "./lyouts/LoginRegister";


const PageRoutes = () => useRoutes([
    {
        element:<LoginRegister/>,
        path:'/login'
    }
]);


export function MainRoutes(){
    return(
        <HashRouter>
            <PageRoutes/>
        </HashRouter>
    );
}