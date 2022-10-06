import {ApplicationApi, Configuration, UserSo} from "../../openapi";
import useApiHook from "../../hooks/useApiHook";

type Props = {
    user: UserSo;
    handleUpdate: () => void;
};

const UserItem = (props: Props) => {

    const api = useApiHook();
    const deleteUser = () => {
        api.deleteUser(props.user.id).then(() => {
            props.handleUpdate();
        });
    }

    return(
        <tr>
            <th scope="row">{props.user.id}</th>
            <td>{props.user.name}</td>
            <td>{props.user.surname}</td>
            <td><button onClick={deleteUser}>Delete</button></td>
        </tr>
    )
}

export default UserItem;