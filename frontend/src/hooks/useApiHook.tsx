import {useMemo} from "react";
import {ApplicationApi, Configuration} from "../openapi";

const useApiHook = (): ApplicationApi => {

    return useMemo(() => {
        return new ApplicationApi(new Configuration({
            basePath: 'http://localhost:3000/api/findev',
        }))
    }, []);
}

export default useApiHook;