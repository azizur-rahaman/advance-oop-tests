'use client';

import { DashboardLayout } from '@/components/layout/DashboardLayout';
import { useAlbums } from '@/features/albums/hooks/useAlbums';
import { Card, CardContent, CardFooter, CardHeader, CardTitle } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { LoadingPage } from '@/components/ui/loading';
import { useToast } from '@/components/ui/toast';
import { FolderOpen, Trash2 } from 'lucide-react';

export default function AlbumsPage() {
  const { albums, loading, error, deleteAlbum } = useAlbums();
  const { showToast, ToastComponent } = useToast();

  const handleDelete = async (id: number) => {
    try {
      await deleteAlbum(id);
      showToast({ message: 'Album deleted successfully', type: 'success' });
    } catch (err) {
      showToast({ message: 'Failed to delete album', type: 'error' });
    }
  };

  if (loading && albums.length === 0) return <DashboardLayout><LoadingPage /></DashboardLayout>;

  return (
    <DashboardLayout>
      {ToastComponent}
      <div className="space-y-6">
        <div>
          <h1 className="text-3xl font-bold text-slate-900">Albums</h1>
          <p className="text-slate-500 mt-1">Browse photo albums</p>
        </div>

        {error && (
          <div className="bg-red-50 text-red-900 p-4 rounded-lg">
            {error}
          </div>
        )}

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {albums.map((album) => (
            <Card key={album.id} className="hover:shadow-md transition-shadow">
              <CardHeader>
                <div className="flex items-center gap-3">
                  <div className="h-12 w-12 rounded-lg bg-pink-100 flex items-center justify-center">
                    <FolderOpen className="h-6 w-6 text-pink-600" />
                  </div>
                  <div className="flex-1">
                    <CardTitle className="text-lg">{album.title}</CardTitle>
                    <p className="text-xs text-slate-500 mt-1">User ID: {album.user.id}</p>
                  </div>
                </div>
              </CardHeader>
              <CardFooter>
                <Button variant="danger" size="sm" onClick={() => handleDelete(album.id)}>
                  <Trash2 className="h-4 w-4 mr-1" />
                  Delete
                </Button>
              </CardFooter>
            </Card>
          ))}
        </div>

        {albums.length === 0 && !loading && (
          <div className="text-center py-12">
            <p className="text-slate-500">No albums found.</p>
          </div>
        )}
      </div>
    </DashboardLayout>
  );
}
