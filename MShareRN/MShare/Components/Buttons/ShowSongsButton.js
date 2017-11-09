import React from 'react';
import { StyleSheet, Button, View, Alert } from 'react-native';
import { StackNavigator } from 'react-navigation';

export default class ShowSongsButton extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        const { navigate } = this.props.navigation;
        return (
            <View style={styles.container}>
                <Button
                    title="Show list of songs"
                    onPress={() => navigate('ShowList')}
                />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        backgroundColor: '#fff',
        marginTop: 30,
        marginHorizontal: 20,
    },
});
