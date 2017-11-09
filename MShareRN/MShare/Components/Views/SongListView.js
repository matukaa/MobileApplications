import React from 'react';
import { StyleSheet, Text, View, ListView, TouchableHighlight } from 'react-native';
import { NavigationActions } from 'react-navigation'

const songs = [
    { title: 'Title1', artist: 'Artist1', album: 'Album1', link: 'www.yt.com/link1' },
    { title: 'Title2', artist: 'Artist2', album: 'Album2', link: 'www.yt.com/link2' },
    { title: 'Title3', artist: 'Artist3', album: 'Album3', link: 'www.yt.com/link3' },
    { title: 'Title4', artist: 'Artist4', album: 'Album4', link: 'www.yt.com/link4' },
]

export default class SongListView extends React.Component {
    static navigationOptions = {
        title: 'Available songs'
    };
    constructor(props) {
        super(props);
        const ds = new ListView.DataSource({ rowHasChanged: (r1, r2) => r1 !== r2 });
        this.state = {
            songDataSource: ds.cloneWithRows(songs),
        };
    }

    renderRow(song, sectionId, rowId, highlightRow) {
        const { navigate } = this.props.navigation;
        return (
            <TouchableHighlight onPress={() => navigate('Edit', { song })}>
                <View style={styles.row}>
                    <Text style={styles.rowTextTitle}>{song.title}</Text>
                    <Text style={styles.rowTextArtist}>{song.artist}</Text>
                    <Text style={styles.rowTextAlbum}>{song.album}</Text>
                </View>
            </TouchableHighlight>
        );
    }

    render() {
        return (
            <ListView
                dataSource={this.state.songDataSource}
                renderRow={this.renderRow.bind(this)}
            />
        );
    }
}

const styles = StyleSheet.create({
    row: {
        flexDirection: 'column',
        justifyContent: 'center',
        padding: 10,
        backgroundColor: '#f4f4f4',
        marginBottom: 3
    },
    rowTextTitle: {
        flex: 1,
        fontWeight: 'bold',
        fontSize: 24,
    },
    rowTextArtist: {
        flex: 1,
        fontStyle: 'italic',
        fontSize: 18,
    },
    rowTextAlbum: {
        flex: 1,
        fontSize: 14,
    },
});
