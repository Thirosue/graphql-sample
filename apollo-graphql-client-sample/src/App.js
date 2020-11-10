import React, { useMemo } from "react";
import { ApolloClient, InMemoryCache, createHttpLink } from '@apollo/client';
import { WebSocketLink } from "@apollo/client/link/ws";
import { gql } from "apollo-boost";
import { ApolloProvider, useMutation, useSubscription } from "@apollo/react-hooks";

const App = () => {
  // Apollo Clientを初期化する
  const client = new ApolloClient({
    link: createHttpLink({
      uri: '/graphql'
    }),
    cache: new InMemoryCache()
  })

  const wsClient = new ApolloClient({
    link: new WebSocketLink({
      uri: 'ws://localhost:8080/subscriptions',
      options: {
        reconnect: true
      }
    }),
    cache: new InMemoryCache()
  })

  return (
    <div className="App">
      <ApolloProvider client={client}>
        <Mutation />
      </ApolloProvider>
      <ApolloProvider client={wsClient}>
        <Subscription />
      </ApolloProvider>
    </div>
  );
};

const Mutation = () => {
  const ADD_LOCATION = gql`
    mutation AddLocation($id: ID!, $name: String!, $path: String!) {
      addLocation(locationId: $id, name: $name, path: $path) {
        locationId
        name
        path
      }
    }
  `;

  let name;
  let path;
  const [addLocation, { data }] = useMutation(ADD_LOCATION);

  return (
    <div>
      <h2>
        Apollo Mutation app{" "}
        <span role="img" aria-label="Rocket">
          🚀
      </span>
      </h2>
      <h3>Mutation:</h3>
      <div>
        <form
          onSubmit={e => {
            e.preventDefault();
            addLocation({ variables: { id: '1', name: name.value, path: path.value } });
            name.value = '';
            path.value = '';
          }}
        >
          <label for="fname">path name:</label><br />
          <input
            ref={node => {
              name = node;
            }}
          /><br />
          <label for="lname">path:</label><br />
          <input
            ref={node => {
              path = node;
            }}
          /><br /><br />
          <button type="submit">Add Location</button>
        </form>
      </div>
    </div>
  );
};

const Subscription = () => {
  const locationSubscription = useMemo(
    () => gql`
      subscription {
            pushLocation {
            locationId
          name
          path
        }
      }
    `,
    []
  );

  // クエリを発行する
  const { loading, error, data } = useSubscription(locationSubscription);

  // 結果を表示する
  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error :(</p>;

  return (
    <div>
      <h2>
        Apollo Subscription app{" "}
        <span role="img" aria-label="Rocket">
          🚀
      </span>
      </h2>
      <h3>Subscription:</h3>
      <div key={data.pushLocation.locationId}>
        <p>id:{data.pushLocation.locationId}</p>
        <p>name:{data.pushLocation.name}</p>
        <p>path:{data.pushLocation.path}</p>
      </div>
    </div>
  );
};

export default App;