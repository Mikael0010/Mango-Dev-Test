'use strict';

// tag::vars[]
const React = require('react'); // <1>
const ReactDOM = require('react-dom'); // <2>
const client = require('./client'); // <3>
// end::vars[]

class App extends React.Component {

    constructor(props){
        super(props);
        this.state={items:[]};
    }

    componentDidMount() { // <2>
		client({method: 'GET', path: '/api/items'}).done(response => {
			this.setState({items: response.entity._embedded.items});
		});
	}

    render(){



    }

}




/*
// tag::app[]
class App extends React.Component { // <1>

	constructor(props) {
		super(props);
		this.state = {users: []};
	}

	componentDidMount() { // <2>
		client({method: 'GET', path: '/api/users'}).done(response => {
			this.setState({users: response.entity._embedded.users});
		});
	}

	render() { // <3>
		return (
			<UserList users={this.state.users}/>
		)
	}
}
// end::app[]

// tag::employee-list[]
class UserList extends React.Component{
	render() {
		const users = this.props.users.map(user =>
			<User key={user._links.self.href} user={user}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Username</th>
						<th>Password</th>
						<th>Useless third one</th>
					</tr>
					{users}
				</tbody>
			</table>
		)
	}
}
// end::employee-list[]

// tag::employee[]
class User extends React.Component{
	render() {
		return (
			<tr>
                <td>{this.props.user.username}</td>
				<td>{this.props.user.password}</td>
				<td>"test"</td>
			</tr>
		)
	}
}
// end::employee[]

// tag::render[]
ReactDOM.render(
	<App />,
	document.getElementById('react')
)
// end::render[]
*/