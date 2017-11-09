import React from 'react';
import { StyleSheet, Button, View, Alert } from 'react-native';

export default class NewSongButton extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        const { navigate } = this.props.navigation;
        return (
            <View style={styles.container}>
                <Button
                    title="Submit a new song"
                    onPress={() => navigate('Submit')}
                />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        backgroundColor: '#fff',
        marginHorizontal: 20,
        marginTop: 30,
    },
});
