import {ApplicationApi, Configuration, UserSo} from "../../openapi";

const conf = new Configuration({
    basePath: 'http://localhost:3000/api/findev',
});

const api = new ApplicationApi(conf);

const UserItem = (props: UserSo) => {

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