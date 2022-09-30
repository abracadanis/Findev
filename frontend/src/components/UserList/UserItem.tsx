import {ApplicationApi, Configuration, UserSo} from "../../openapi";
import useApiHook from "../../hooks/useApiHook";

const UserItem = (props: UserSo) => {

    const api = useApiHook();
    const deleteUser = () => {
        api.deleteUser(props.id).then();
    }

    return(
        <tr>
            <th scope="row">{props.id}</th>
            <td>{props.name}</td>
            <td>{props.surname}</td>
            <td><button onClick={deleteUser}>Delete</button></td>
        </tr>
    )
}

export default UserItem;